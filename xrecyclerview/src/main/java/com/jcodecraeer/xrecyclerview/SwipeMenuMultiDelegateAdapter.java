package com.jcodecraeer.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cantian on 2018/1/23.
 */

public class SwipeMenuMultiDelegateAdapter<T> extends SwipeMenuAdapter {
    private Context m_context;
    private List<T> mDatas;
    private ItemViewDelegateManager mItemViewDelegateManager = new ItemViewDelegateManager();

    public SwipeMenuMultiDelegateAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public SwipeMenuMultiDelegateAdapter addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
        return this;
    }

    public SwipeMenuMultiDelegateAdapter addItemViewDelegate(int viewType, ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(viewType, itemViewDelegate);
        return this;
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        if (m_context == null) {
            m_context = parent.getContext();
        }
        int itemViewLayoutId = mItemViewDelegateManager.getItemViewLayoutId(viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(itemViewLayoutId, null);
        return view;
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return ViewHolder.createViewHolder(m_context,realContentView);
    }

   

    private void convert(ViewHolder holder, T t) {
        mItemViewDelegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        convert((ViewHolder) holder, mDatas.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (!useItemViewDelegateManager()) return super.getItemViewType(position);
        return mItemViewDelegateManager.getItemViewType(mDatas.get(position), position);
    }
    protected boolean useItemViewDelegateManager() {
        return mItemViewDelegateManager.getItemViewDelegateCount() > 0;
    }
}
