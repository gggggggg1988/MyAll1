package fragment.homefragment.biz;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class SwipeCardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        detachAndScrapAttachedViews(recycler);//在layout布局之前，将所有的子view全部detach掉，然后放入到scrab集合中
        //然后将view集合中最上面的四个放入recyclerview中
        int itemCount = getItemCount();
        int bottomPosition  = itemCount-CardConfig.MAX_SHOW_COUNT;
        for (int i = bottomPosition; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChild(view,0,0);
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);//考虑间隔线的宽度
            layoutDecorated(view,widthSpace/2,heightSpace/2,widthSpace/2+getDecoratedMeasuredWidth(view),heightSpace/2+getDecoratedMeasuredHeight(view));
            int level = itemCount-i-1;
           if (level>0) {
                if (level<CardConfig.MAX_SHOW_COUNT-1) {
                view.setTranslationY(level*CardConfig.TRANS_Y_GAP);
                view.setScaleX(1-CardConfig.SCALE_GAP*level);
                view.setScaleY(1-CardConfig.SCALE_GAP*level);
                }else{
                    view.setTranslationY((level-1)*CardConfig.TRANS_Y_GAP);
                    view.setScaleX(1-CardConfig.SCALE_GAP*(level-1));
                    view.setScaleY(1-CardConfig.SCALE_GAP*(level-1));
                }
           }else{
           
           }
        }

    }
}
