package fragment.homefragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fragment.homefragment.viewHolder.SimpleItemViewHolder;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.BaseRecyclerAdapter;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.moduleView.ModuleViewBean;

public class SimpleDataAdapter extends BaseRecyclerAdapter<ModuleViewBean>{

    public SimpleDataAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onMyCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new SimpleItemViewHolder(parent.getContext(),view);
    }

    @Override
    public void onMyBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        SimpleItemViewHolder holder1 = (SimpleItemViewHolder) holder;
        TextView view = holder1.getView(android.R.id.text1);
        view.setText(getList().get(position).getTitle());
        holder1.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class claz = getList().get(position).getC();
                if (claz != null) {
                    Intent in = new Intent(getContext(), getList().get(position).getC());
                    getContext().startActivity(in);
                }
            }
        });
    }
}
