package feicuiedu.test01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/18.
 */
public class MyAdapter extends BaseAdapter {
    private List<Map<String, Object>> mList;

    private Context mContext;
    public MyAdapter(List<Map<String, Object>> list, Context context) {
        mList = list;
        mContext = context;
    }
    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String, Object> map = mList.get(position);
        //复用coverView   ListView-->item
        if (convertView == null) {
            //使用Inflater来填充item布局
            convertView = LayoutInflater.from(mContext).inflate(R.layout.show_item, null);
        }

        //使用View对象里的findViewById找到show_item里的控件
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        //给对象赋值
        if (!map.isEmpty()) {
            textView.setText((String) map.get("context"));
        }
        return convertView;
    }
}
