package fragment.homefragment;


import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.myall.R;
import com.jcodecraeer.xrecyclerview.Closeable;
import com.jcodecraeer.xrecyclerview.OnSwipeMenuItemClickListener;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.SwipeMenuAdapter;
import com.jcodecraeer.xrecyclerview.SwipeMenuCreator;
import com.jcodecraeer.xrecyclerview.SwipeMenuItem;
import com.jcodecraeer.xrecyclerview.SwipeMenuRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import entity.Data;
import fragment.BaseFragment;
import fragment.homefragment.adapter.ArmyMultiAdapter;

import static util.Consts.BASEURL;
import static util.Consts.PAGE;
import static util.Consts.TABLE_NUM;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArmyFragment extends BaseFragment {
    private List<Data> temp = new ArrayList<>();
    private SparseArray m_array = new SparseArray();
    private SwipeMenuAdapter adapter;
    private List<Data> result = new ArrayList<>();
    public static final int []RES = new int[]{R.mipmap.image5,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image6,R.mipmap.image7,R.mipmap.image8};
    public static final int []BANNER = new int[]{R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5};
    private MZBannerView header;

    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mActivity, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(mActivity, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }

            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                result.remove(adapterPosition);
                adapter.notifyItemRemoved(adapterPosition);
            }
        }
    };

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {

        @Override
        public void onCreateMenu(com.jcodecraeer.xrecyclerview.SwipeMenu swipeLeftMenu, com.jcodecraeer.xrecyclerview.SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
//                SwipeMenuItem addItem = new SwipeMenuItem(mActivity)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.ic_action_add) // 图标。
//                        .setWidth(width) // 宽度。
//                        .setHeight(height); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//
//                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_delete)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

//                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity)
//                        .setBackgroundDrawable(R.drawable.selector_purple)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem addItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setText("取消")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };
    public ArmyFragment() {
        // Required empty public constructor

    }

    @Override
    protected void initData() {
        super.initData();
        getData(2, 1);
//        adapter = new ArmyFragAdapter(mActivity, result);
        adapter = new ArmyMultiAdapter(result);
        rv.setSwipeMenuCreator(swipeMenuCreator);
        rv.setSwipeMenuItemClickListener(menuItemClickListener);
        rv.setAdapter(adapter);
        rv.refresh();
    }

    private void getData(int table, int page) {
        final String url = BASEURL + TABLE_NUM + table + PAGE + page;
//        protocal.loadData(url,handler);
        result.clear();
        for (int i = 0; i < 10 * page; i++) {
            result.add(new Data("table:" + table + "title:" + "liwei" + i + "page:" + page));
        }
//        result.addAll(temp);
    }

    private XRecyclerView rv;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (XRecyclerView) view.findViewById(R.id.army_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayout.VERTICAL));

        View header_layout = LayoutInflater.from(getActivity()).inflate(R.layout.header_layout, null);
        header = (MZBannerView) header_layout.findViewById(R.id.banner);
        header.setIndicatorVisible(true);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<RES.length;i++){
            list.add(RES[i]);
        }
        header.setPages(list, new MZHolderCreator<HeaderViewHolder>() {
            @Override
            public HeaderViewHolder createViewHolder() {
                return new HeaderViewHolder();
            }
        });
        LinearLayout parent = (LinearLayout) header.getParent();
        parent.removeView(header);
        View view1 = LayoutInflater.from(mActivity).inflate(R.layout.recyclerview_header, (ViewGroup) mActivity.findViewById(android.R.id.content), false);
        rv.addHeaderView(header);


        rv.setArrowImageView(R.drawable.iconfont_downgrey);
        rv.setRefreshProgressStyle(ProgressStyle.BallScale);
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            public int count = 1;

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getData(3, 1);
                        adapter.notifyDataSetChanged();
                        rv.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                count++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(3,count);
                        adapter.notifyDataSetChanged();
                rv.loadMoreComplete();
                    }
                },1000);

            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_army, container, false);
    }
    class HeaderViewHolder implements MZViewHolder<Integer> {

        ImageView img;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            img = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            img.setImageResource(data);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        header.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        header.start();
    }
}
