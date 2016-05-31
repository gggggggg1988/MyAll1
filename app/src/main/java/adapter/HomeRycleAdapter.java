package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myall.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import MyInterface.RecyclerItemClickListener;
import entity.NewsBean;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class HomeRycleAdapter extends RecyclerView.Adapter {
    private RecyclerItemClickListener itemListener;

    public void setOnItemListener(RecyclerItemClickListener itemListener) {//因为adapter是管理item的，所以声明，和设置监听器都放在了adapter里面
        this.itemListener = itemListener;
    }

    private List<NewsBean.ResultBean> list;
private BitmapUtils m_bitmapUtils;
    private Context context;
    public HomeRycleAdapter(Context context,List<NewsBean.ResultBean> list) {
        this.list = list;
        this.context = context;
    }
    class RecyclerHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private TextView title,comment,from;

        public TextView getTitle() {
            return title;
        }

        public TextView getComment() {
            return comment;
        }

        public TextView getFrom() {
            return from;
        }

        public RecyclerHolder1(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.textView5);
            this.from = (TextView) itemView.findViewById(R.id.from);
            this.comment = (TextView) itemView.findViewById(R.id.comment);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
     private ImageView icon;
      private   TextView title,comment,from;
        private RecyclerItemClickListener itemListener;
        public ImageView getIcon() {
            return icon;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getComment() {
            return comment;
        }

        public TextView getFrom() {
            return from;
        }

        public void setIcon(ImageView icon) {
            this.icon = icon;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public void setComment(TextView comment) {
            this.comment = comment;
        }

        public void setFrom(TextView from) {
            this.from = from;
        }

        public RecyclerHolder(View view,RecyclerItemClickListener listener) {
            super(view);
            icon = (ImageView) view.findViewById(R.id.imageView3);
            title = (TextView) view.findViewById(R.id.textView2);
            comment = (TextView) view.findViewById(R.id.textView3);
            from = (TextView) view.findViewById(R.id.textView4);
            view.setOnClickListener(this);
            itemListener = listener;

        }

        @Override
        public void onClick(View v) {
            itemListener.onItemClickListener(v,getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    //建立枚举 2个item 类型
    public enum ITEM_TYPE { ITEM1, ITEM2 }

    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
         return list.get(position).getPicUrl().equals(null) ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据取得的不同的view类型，返回不同的holder
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new RecyclerHolder1(View.inflate(parent.getContext(),R.layout.news_list_item_layout3,null));

        }else{

            return new RecyclerHolder(View.inflate(parent.getContext(),R.layout.news_list_item_layout,null),  itemListener  );
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //对不同的布局加载相应的数据
        if (holder instanceof RecyclerHolder) {

            RecyclerHolder hold = (RecyclerHolder)holder;
            ((RecyclerHolder)holder).getTitle().setText(list.get(position).getTitle());
            hold.getFrom().setText(list.get(position).getCtime());
            m_bitmapUtils = new BitmapUtils( context);
            m_bitmapUtils.display(hold.getIcon(),list.get(position).getPicUrl());
        }else if(holder instanceof RecyclerHolder1){
            RecyclerHolder1 hold = (RecyclerHolder1) holder;
            hold.getTitle().setText(list.get(position).getTitle());
            hold.getFrom().setText(list.get(position).getDescription());
            hold.getComment().setText(list.get(position).getCtime());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
