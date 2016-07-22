package feicuiedu.test01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mnum, mname, mtel;
    Button   mclick;
    ListView mshow;
    private UserDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnum = (EditText) findViewById(R.id.num);
        mname = (EditText) findViewById(R.id.name);
        mtel = (EditText) findViewById(R.id.tel);
        mclick = (Button) findViewById(R.id.click);
        mshow = (ListView) findViewById(R.id.show);
        mDao = new UserDao(this);
        mshow.setAdapter(mAdapter);
        mclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number=mnum.getText().toString().trim();
                String name=mname.getText().toString().trim();
                String tel=mtel.getText().toString().trim();

                  //校检数据
                if(TextUtils.isEmpty(number)||TextUtils.isEmpty(name)||TextUtils.isEmpty(tel)){
                    Toast.makeText(MainActivity.this,"请检查输入的数据是否为空！",Toast.LENGTH_SHORT).show();
                      //结束方法，不往下执行
                    return;
                }
                if(mDao.find(number)){
                    Toast.makeText(MainActivity.this,"插入的ID已存在！",Toast.LENGTH_SHORT).show();
                    return;
                }
                  boolean result = mDao.add(number,name,tel);
                if(result){
                    Toast.makeText(MainActivity.this,"插入成功！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"插入失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
           return  mDao.getTotal();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            UserInfo userInfo = mDao.getUserInfo(position);
            //number
            TextView tvNumber =new TextView(MainActivity.this);
            tvNumber.setText(userInfo.number);
            //获得当前窗体的对象 设置对应宽度
            int  width=getWindow().getWindowManager().getDefaultDisplay().getWidth();
            //设置布局的具体属性
            tvNumber.setLayoutParams(new LinearLayout.LayoutParams(width/3,50));
            //name
            TextView tvName =new TextView(MainActivity.this);
            tvName.setText(userInfo.name);
            tvName.setLayoutParams(new LinearLayout.LayoutParams(width/3,50));
            //tel
            TextView tvTel =new TextView(MainActivity.this);
            tvTel.setText(userInfo.tel);
            tvTel.setLayoutParams(new LinearLayout.LayoutParams(width/3,50));
            LinearLayout layout = new LinearLayout(MainActivity.this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.addView(tvNumber);
            layout.addView(tvName);
            layout.addView(tvTel);
            return layout;
        }

    };
}
