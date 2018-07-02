package adapter;

import android.content.Context;

import com.example.administrator.myall.R;

import entity.Data;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ItemViewDelegate;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ItemTwoDelegate implements ItemViewDelegate<Data> {
    public ItemTwoDelegate(Context context) {
        m_context = context;
    }

    private Context m_context;
    @Override
    public int getItemViewLayoutId() {
        return R.layout.news_list_item_layout3;
    }

    @Override
    public boolean isForViewType(Data item, int position) {
        return position%2!=0;
    }

    @Override
    public void convert(ViewHolder holder, Data data, int position) {
//        Glide.with()
        holder.setText(R.id.textView5,data.getTitle());
        holder.setText(R.id.from, data.getSource());
        holder.setText(R.id.comment, data.getReply_count());
    }
}
