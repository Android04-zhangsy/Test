package feicuiedu.test01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**学生类的DAO类
 * 插入、删除数据
 * Created by Administrator on 2016/7/18.
 */
public class UserDao {
    //创建数据库
    UserDBHelper mDBHelper;
    public static final String TAG="UserDao";
    public UserDao(Context context){
       mDBHelper = new UserDBHelper(context);
    }
   //实现一个添加数据的方法
    public boolean add(String  number,String name,String tel){
                //打开数据库进行插入数据
        SQLiteDatabase  db = mDBHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("number",number);
        values.put("name",name);
        values.put("tel",tel);
        long insert = db.insert(UserDBHelper.INFO,null,values);
        //如果insert是-1说明插入的数据有问题
           if(insert != -1){
             //插入成功
               return true;
           }else {
               Log.d(TAG,"add:"+"方法有问题");
               return false;
           }
    }
    //实现一个 查询 数据库 判断ID的方法 保证ID不重复
   public boolean find(String studentNumber){
     boolean result = false;
       SQLiteDatabase db = mDBHelper.getReadableDatabase();
       //selection 数据库查询的条件语句
       Cursor cursor = db.query(UserDBHelper.INFO, null, "number=?", new String[]{studentNumber}, null, null, null);
      if(cursor.moveToFirst()){
          result=true;
      }
       cursor.close();
       db.close();
       return result;
   }
    //实现一个取出数据的方法，获得的是具体的数据  从数据库里取出数据
    public UserInfo getUserInfo(int position){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        //column里面取出key
        Cursor cursor =db.query(UserDBHelper.INFO,
                new String[]{"number","name","tel"},null,null,null,null,null);
        cursor.moveToPosition(position);
        String number = cursor.getString(0);
        String name = cursor.getString(1);
        String tel = cursor.getString(2);
        UserInfo user = new UserInfo(number,name,tel);
        cursor.close();
        db.close();
        return  user;

    }
    /**
     * 取出数据库里的所有内容
     */
         public  int getTotal(){
             SQLiteDatabase db = mDBHelper.getReadableDatabase();
             Cursor cursor =db.query(UserDBHelper.INFO, null,null,null,null,null,null);
             int count =cursor.getCount();
             cursor.close();
             db.close();
             Log.d(TAG,"getTotal:"+count);
             return  count;
         }

}

