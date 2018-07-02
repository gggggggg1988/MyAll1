package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myall.R;
import com.jcodecraeer.xrecyclerview.ItemViewDelegateManager;
import com.jcodecraeer.xrecyclerview.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

import entity.Data;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class ArmyFragAdapter extends SwipeMenuAdapter<RecyclerView.ViewHolder> {
    private final ItemViewDelegateManager mItemViewDelegateManager;
    private  Context m_context;
    private List<Data> datas = new ArrayList();
    public ArmyFragAdapter(Context context, List<Data> datas) {
        this.datas = datas;
        m_context = context;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(m_context).inflate(R.layout.news_list_item_layout2,null);
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new RecyclerHolder1(realContentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerHolder1 hold = (RecyclerHolder1) holder;
        hold.getTitle().setText(datas.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class RecyclerHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView title,comment,from;

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getComment() {
            return comment;
        }

        public void setComment(TextView comment) {
            this.comment = comment;
        }

        public TextView getFrom() {
            return from;
        }

        public void setFrom(TextView from) {
            this.from = from;
        }

        public View convertView;
        public RecyclerHolder1(View itemView) {
            super(itemView);
            convertView = itemView;
            this.title = (TextView) itemView.findViewById(R.id.textView5);
            this.from = (TextView) itemView.findViewById(R.id.from);
            this.comment = (TextView) itemView.findViewById(R.id.comment);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
