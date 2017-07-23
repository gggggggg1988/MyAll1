package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.myall.LoadingMoreViewTest;
import com.example.administrator.myall.R;

import entity.Data;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ItemViewDelegate;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ItemOneDelegate implements ItemViewDelegate<Data> {
    public ItemOneDelegate(Context context) {
        m_context = context;
    }

    private Context m_context;

    @Override
    public int getItemViewLayoutId() {
        return R.layout.news_list_item_layout;
    }

    @Override
    public boolean isForViewType(Data item, int position) {
        return position%2==0;
    }

    @Override
    public void convert(ViewHolder holder, Data dataOne, final int position) {
        holder.setText(R.id.textView2, dataOne.getTitle());
        ImageView iv = holder.getView(R.id.imageView3);
        Glide.with(m_context).load(dataOne.getText_image0()).placeholder(R.mipmap.ic_launcher).into(iv);
        holder.setText(R.id.textView3, dataOne.getEditTime());

        holder.setText(R.id.textView4, dataOne.getReply_count());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 2) {
                    Intent in = new Intent(m_context, LoadingMoreViewTest.class);
                    m_context.startActivity(in);
                }
            }
        });
    }
}
