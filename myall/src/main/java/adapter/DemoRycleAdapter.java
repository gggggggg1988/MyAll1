package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myall.R;
import com.example.administrator.myall.activitys.ChenJinshi;
import com.example.administrator.myall.activitys.MVPtest;
import com.example.administrator.myall.activitys.RetrofitActivity;
import com.example.administrator.myall.activitys.RoundIconActivity;
import com.example.administrator.myall.activitys.StickyPulldown;
import com.jcodecraeer.xrecyclerview.SwipeMenuMultiDelegateAdapter;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import MyInterface.RecyclerItemClickListener;
import activitypack.JobSchedulerKeepAliveAct;
import entity.Data;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class DemoRycleAdapter extends SwipeMenuMultiDelegateAdapter {
    private RecyclerItemClickListener itemListener;

    public void setOnItemListener(RecyclerItemClickListener itemListener) {//因为adapter是管理item的，所以声明，和设置监听器都放在了adapter里面
        this.itemListener = itemListener;
    }

    private List<Data> list;
private BitmapUtils m_bitmapUtils;
    private Context context;
    public DemoRycleAdapter(Context context, List<Data> list) {
        super(list);
        this.list = list == null ? new ArrayList<Data>() : list;
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
    class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
     private ImageView icon;
        public View convertView;
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

        public RecyclerHolder(View view) {
            super(view);
            convertView = view;
            icon = (ImageView) view.findViewById(R.id.imageView3);
            title = (TextView) view.findViewById(R.id.textView2);
            comment = (TextView) view.findViewById(R.id.textView3);
            from = (TextView) view.findViewById(R.id.textView4);
//            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
//            itemListener.onItemClickListener(v,getAdapterPosition());//有点多次一举
            //可以直接做点击事件逻辑,如下
            //Toast.makeText(context, "被点的是position" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
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
         return list.get(position).getTop_image().equals(null) ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return View.inflate(parent.getContext(),R.layout.news_list_item_layout3,null);

        }else{
            return View.inflate(parent.getContext(),R.layout.news_list_item_layout,null);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        //根据取得的不同的view类型，返回不同的holder
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new RecyclerHolder1(realContentView);

        }else{

            return new RecyclerHolder(realContentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //对不同的布局加载相应的数据
        if (holder instanceof RecyclerHolder) {

            RecyclerHolder hold = (RecyclerHolder)holder;
            ((RecyclerHolder)holder).getTitle().setText(list.get(position).getTitle());
            hold.getFrom().setText(list.get(position).getSource());
            m_bitmapUtils = new BitmapUtils( context);
            m_bitmapUtils.display(hold.getIcon(),list.get(position).getTop_image());
            hold.getComment().setText(list.get(position).getReply_count()+"");
            hold.convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    LogUtils.i("第"+position+"项被点了");
                    switch (position) {
                             case 0:
                                 Intent in = new Intent(context, JobSchedulerKeepAliveAct.class);
                                 in.putExtra("data", list.get(position));
                                 context.startActivity(in);
                                 break;
                        case 1:
                            Intent in1 = new Intent(context, RetrofitActivity.class);
                            in1.putExtra("RetrofitActivity", list.get(position));
                            context.startActivity(in1);
                            break;
                        case 2:
                            Intent in2 = new Intent(context, StickyPulldown.class);
                            in2.putExtra("StickyPulldown", list.get(position));
                            context.startActivity(in2);
                            break;
                        case 3:
                            Intent in3 = new Intent(context, ChenJinshi.class);
                            in3.putExtra("ChenJinshi", list.get(position));
                            context.startActivity(in3);
                            break;
                        case 4:
                            Intent in4 = new Intent(context, RoundIconActivity.class);
                            in4.putExtra("xfermode圆形头像", list.get(position));
                            context.startActivity(in4);
                            break;
                        case 5:
                            Intent in5 = new Intent(context, MVPtest.class);
                            context.startActivity(in5);
                            break;

                             default:
                                 break;
                           }


                }
            });

            hold.getIcon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.i("第"+position+"项图片被点了");


                }
            });

        }else if(holder instanceof RecyclerHolder1){
            RecyclerHolder1 hold = (RecyclerHolder1) holder;
            hold.getTitle().setText(list.get(position).getTitle());
            hold.getFrom().setText(list.get(position).getSource());
            hold.getComment().setText(list.get(position).getReply_count()+"");

            ((RecyclerHolder1) holder).convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.i("第"+position+"项被点了");

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
