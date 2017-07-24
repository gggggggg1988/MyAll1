package ndkjnidemo.wobiancao.com.recyclerviewstudy;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public interface ItemTouchHelperAdapterCallback {
    boolean onItemMove(int fromPosition,int toPosition);

    void onItemSwipe(int adapterPosition);

}
