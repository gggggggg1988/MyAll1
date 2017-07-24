package ndkjnidemo.wobiancao.com.recyclerviewstudy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class RecyclerViewAdapter extends StickyRecyclerView.StickyAdapter {
    private  List<String> data ;
    private Context context;

    public RecyclerViewAdapter(List<String> data) {

        this.data = data==null ? new ArrayList<String>() : data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(View.inflate(parent.getContext(),R.layout.item_view,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder hold = (ViewHolder) holder;
        TextView view = hold.getTextView();
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        hold.getTextView().setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public String getItemViewTitle(int position) {
        return ""+position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView m_textView;

        public TextView getTextView() {
            return m_textView;
        }



        public ViewHolder(View itemView) {
            super(itemView);
            m_textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
