package fragment.homefragment.adapter;

import com.jcodecraeer.xrecyclerview.SwipeMenuMultiDelegateAdapter;

import java.util.List;

import entity.Data;

/**
 * Created by cantian on 2018/1/23.
 */

public class ArmyMultiAdapter extends SwipeMenuMultiDelegateAdapter<Data> {
    public ArmyMultiAdapter(List<Data> mDatas) {
        super(mDatas);
        addItemViewDelegate(new ArmyItemDelegateOne());
        addItemViewDelegate(new ArmyItemDeletateTwo());

    }


}
