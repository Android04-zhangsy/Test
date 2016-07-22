package feicuiedu.test01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/18.
 */
public class UserDBHelper extends SQLiteOpenHelper {
    public static final String INFO = "info";
    public UserDBHelper(Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          //执行创建数据库的语句
        db.execSQL("create table " + INFO + "(Id INTEGER PRIMARY KEY AUTOINCREMENT,number VARCHAR(20)," +
                "name VARCHAR(20),tel VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
