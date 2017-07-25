package adapter;

import android.content.Context;

import java.util.List;

import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.MultiItemTypeAdapter;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ArmyFragAdapter extends MultiItemTypeAdapter {
    public ArmyFragAdapter(Context context, List datas) {
        super(context, datas);
        addItemViewDelegate(new ItemOneDelegate(context));
        addItemViewDelegate(new ItemTwoDelegate(context));
    }

}
