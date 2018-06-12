package com.example.administrator.myall.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.administrator.myall.R;
import com.example.administrator.myall.utils.LogUtils;

/**
 * Created by cantian on 2018/1/18.
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration{
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    private Drawable mDivider;
    private int rowCount;


    public DividerGridItemDecoration(Context context)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        mDivider = context.getDrawable(R.drawable.decoration_divider);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
    {//这个时候垂直和水平方向都需要重新绘制
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    private int getSpanCount(RecyclerView parent)
    {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }


        return spanCount;
    }

    public void drawHorizontal(Canvas c, RecyclerView parent)
    {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin
                    + mDivider.getIntrinsicWidth();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);

            if (!isLastRaw(parent,i,getSpanCount(parent),childCount)) {
                mDivider.draw(c);
            }

        }
    }

    public void drawVertical(Canvas c, RecyclerView parent)
    {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            final View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
//            LogUtils.i("left----"+child.getRight()+"right------"+left + mDivider.getIntrinsicWidth());
            mDivider.setBounds(left, top, right, bottom);
//            LogUtils.i("i---"+i+"spancount---"+getSpanCount(parent));
            if (!isLastColum(parent,i,getSpanCount(parent),childCount)) {
                mDivider.draw(c);
            }
        }
    }

    /**
     * 判断是否是最后一列
     */
    private boolean isLastColum(RecyclerView parent, int pos, int spanCount,int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else
            {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    /**
     * 是否是最后一行
     */
    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,int childCount)
    {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
//            childCount = childCount - childCount % spanCount;

            if (childCount%spanCount==0) {

//            rowCount = childCount/spanCount;
//                LogUtils.i("(childCount / spanCount - 1)---"+(childCount / spanCount - 1));
//                LogUtils.i("(childCount/spanCount-1)*spanCount-1---" + ((childCount / spanCount - 1) * spanCount - 1));
//                LogUtils.i("childCount---  "+childCount+"pos----"+pos+"is last---"+(pos > (childCount/spanCount-1)*spanCount-1));
                if (pos > (childCount/spanCount-1)*spanCount-1)// 如果是最后一行，则不需要绘制底部
                    return true;
            }else{
//            rowCount = childCount/spanCount+1;
                LogUtils.i("childCount---  "+childCount+"pos----"+pos+"is last---"+(pos > (childCount/spanCount)*spanCount-1));
                if (pos > (childCount/spanCount)*spanCount-1)// 如果是最后一行，则不需要绘制底部
                    return true;
            }


        } else if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL)
            {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent)
    {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
//        int childCount = parent.getChildCount();

        if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
        {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
        else if(isLastRaw(parent, itemPosition, spanCount, childCount)&&isLastColum(parent, itemPosition, spanCount, childCount)){
            outRect.set(0, 0, 0, 0);
        }
        else
        {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                    mDivider.getIntrinsicHeight());
        }
    }
}
