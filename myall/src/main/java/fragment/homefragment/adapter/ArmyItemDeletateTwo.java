package fragment.homefragment.adapter;

import com.example.administrator.myall.R;
import com.jcodecraeer.xrecyclerview.ItemViewDelegate;
import com.jcodecraeer.xrecyclerview.ViewHolder;

import entity.Data;

/**
 * Created by cantian on 2018/1/23.
 */

public class ArmyItemDeletateTwo implements ItemViewDelegate<Data> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.news_list_item_layout2;
    }

    @Override
    public boolean isForViewType(Data item, int position) {
        return position%2!=0;
    }

    @Override
    public void convert(ViewHolder holder, Data data, int position) {
        holder.setText(R.id.textView5, data.getTitle());
    }
}
