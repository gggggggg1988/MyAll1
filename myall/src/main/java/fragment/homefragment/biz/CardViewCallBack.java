package fragment.homefragment.biz;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import fragment.homefragment.adapter.TanTanViewAdapter;
import fragment.homefragment.bean.TanTanViewBean;

public class CardViewCallBack extends ItemTouchHelper.SimpleCallback {
    private final RecyclerView recyclerview;
    private final TanTanViewAdapter adapter;
    private List<TanTanViewBean> datas;
    public CardViewCallBack(RecyclerView recyclerView, TanTanViewAdapter adapter, List<TanTanViewBean> data) {
        super(0, ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);//控制可以往哪个方向拖动删除
        this.recyclerview = recyclerView;
        this.adapter = adapter;
        this.datas = data;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        TanTanViewBean remove = datas.remove(viewHolder.getLayoutPosition());
        datas.add(0,remove);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        double distance = Math.sqrt(dX * dX + dY * dY);
        double maxDistance = recyclerView.getWidth()*0.5f;
        double fraction = distance/maxDistance;
        if (fraction >1) {
            fraction = 1;
        }
        int itemCount = recyclerView.getChildCount();

        for (int i = 0; i < itemCount; i++) {
            View view = recyclerView.getChildAt(i);
            int level = itemCount-i-1;
            if (level>0) {
                if (level<CardConfig.MAX_SHOW_COUNT-1) {
                    view.setTranslationY((float) (level*CardConfig.TRANS_Y_GAP-CardConfig.TRANS_Y_GAP*fraction));
                    view.setScaleX((float) (1-CardConfig.SCALE_GAP*level+CardConfig.SCALE_GAP*fraction));
                    view.setScaleY((float) (1-CardConfig.SCALE_GAP*level+CardConfig.SCALE_GAP*fraction));
                }else{

                }
            }else{

            }
        }
    }
}
