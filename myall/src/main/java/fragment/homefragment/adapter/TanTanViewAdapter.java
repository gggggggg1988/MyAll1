package fragment.homefragment.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myall.R;

import java.util.List;

import fragment.homefragment.bean.TanTanViewBean;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.CommonAdapter;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ViewHolder;

public class TanTanViewAdapter extends CommonAdapter<TanTanViewBean> {

    public TanTanViewAdapter(Context context, int layoutId, List<TanTanViewBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TanTanViewBean tanTanViewBean, int position) {
         ImageView pic;
         TextView name;
         Button select;
        pic = holder.getView(R.id.pic);
        name = holder.getView(R.id.name);
        select = holder.getView(R.id.select);

        pic.setImageResource(tanTanViewBean.getPicRes());
        name.setText(tanTanViewBean.getName());
    }
}
