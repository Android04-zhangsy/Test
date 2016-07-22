package feicuiedu.test01;

/**
 * Created by Administrator on 2016/7/18.
 */
public class UserInfo {
    //唯一 ID
    public   String number;
    //名字
    public String name;
    //电话
    public  String tel;
    //重写构造方法
    public UserInfo( String number,String name,String tel) {
        super();
        this.number = number;
        this.name = name;
        this.tel=tel;

    }

}
