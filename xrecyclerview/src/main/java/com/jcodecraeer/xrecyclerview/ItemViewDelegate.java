package com.jcodecraeer.xrecyclerview;


import android.support.v7.widget.RecyclerView;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(RecyclerView.ViewHolder holder, T t, int position);

}
