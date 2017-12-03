package fragment.homefragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myall.R;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.vise.log.ViseLog;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.CacheMode;
import com.vise.xsnow.http.mode.CacheResult;
import com.vise.xsnow.ui.status.StatusLayoutManager;

import java.util.List;

import MyInterface.RecyclerItemClickListener;
import adapter.HomeRycleAdapter;
import entity.JuHeNewsBean;
import entity.News;
import fragment.BaseFragment;
import http.NewsProtocal;
import io.supercharge.shimmerlayout.ShimmerLayout;
import ndkjnidemo.wobiancao.com.recyclerview_swipe.Closeable;
import ndkjnidemo.wobiancao.com.recyclerview_swipe.OnSwipeMenuItemClickListener;
import ndkjnidemo.wobiancao.com.recyclerview_swipe.SwipeMenu;
import ndkjnidemo.wobiancao.com.recyclerview_swipe.SwipeMenuCreator;
import ndkjnidemo.wobiancao.com.recyclerview_swipe.SwipeMenuItem;
import ndkjnidemo.wobiancao.com.recyclerview_swipe.SwipeMenuRecyclerView;
import util.Consts;
import widget.DividerLine;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportantFragment extends BaseFragment implements Consts, SwipeRefreshLayout.OnRefreshListener {
    private SwipeMenuRecyclerView m_recyclerView;
    private BitmapUtils m_bitmapUtils;
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager m_layoutManager;
    private HomeRycleAdapter adapter;
    private StatusLayoutManager mStatusLayoutManager;
    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem addItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
                        .setImage(R.mipmap.ic_action_add) // 图标。
                        .setWidth(width) // 宽度。
                        .setHeight(height); // 高度。
                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);

                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
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

                SwipeMenuItem closeItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_action_close)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem addItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.drawable.selector_green)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };
    private List<JuHeNewsBean.Data> result;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                String JSON = (String) msg.obj;
                Gson gson = new Gson();
                News news = gson.fromJson(JSON, News.class);
//                result = news.getData();
//                adapter = new HomeRycleAdapter(mActivity, result);
//
//                adapter.setOnItemListener(new RecyclerItemClickListener() {
//                    @Override
//                    public void onItemClickListener(View view, int position) {
//
//                        Toast.makeText(mActivity, "被点的是position" + position, Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//                m_recyclerView.setSwipeMenuCreator(swipeMenuCreator);
//                m_recyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
//                m_recyclerView.setAdapter(adapter);
//                refreshLayout.setRefreshing(false);
//            layout.stopShimmerAnimation();
            }

        }
    };

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
    private int lastVisibleItem, currentPage;
    private NewsProtocal protocal = new NewsProtocal();
    private ShimmerLayout layout;


    public ImportantFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

//        mStatusLayoutManager = StatusLayoutManager.newBuilder(getActivity())
//                .contentView(R.layout.fragment_important)
//                .loadingView(R.layout.loading_layout)
//                .emptyView(R.layout.empty_layout)
//                .networkErrorView(R.layout.neterror_layout)
//                .otherErrorView(R.layout.error_layout)
//                .retryViewId(R.id.reload_view)
//                .onStatusViewListener(new OnStatusViewListener() {
//                    @Override
//                    public void onShowView(View view, int id) {
//
//                    }
//
//                    @Override
//                    public void onHideView(View view, int id) {
//
//                    }
//                })
//                .onRetryListener(new OnRetryListener() {
//                    @Override
//                    public void onRetry() {
//                        mStatusLayoutManager.showLoadingView();
//
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    Thread.sleep(3000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        mStatusLayoutManager.showContentView();
//                                    }
//                                });
//                            }
//                        }).start();
//                    }
//                }).build();
//        mLayoutMain.addView(mStatusLayoutManager.getStatusLayout());
//        mStatusLayoutManager.showLoadingView();
        m_recyclerView = (SwipeMenuRecyclerView) view.findViewById(R.id.home_recycler_view);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        //refreshLayout.setColorScheme(Color.RED,Color.GREEN,Color.BLUE,Color.BLACK);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        m_layoutManager = new LinearLayoutManager(mActivity);
        m_recyclerView.setLayoutManager(m_layoutManager);
        m_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
//                    refreshLayout.setRefreshing(true);
                    currentPage = (lastVisibleItem + 1) / 15;
                    getData(1, 15 * (currentPage + 1));

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    refreshLayout.setRefreshing(false);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = m_layoutManager.findLastVisibleItemPosition();
            }
        });
        DividerLine divider = new DividerLine(LinearLayoutManager.VERTICAL);
        divider.setSize(2);
        divider.setColor(Color.GRAY);
        m_recyclerView.addItemDecoration(divider);
//        getData(1);
        // Required empty public constructor
        layout = (ShimmerLayout) view.findViewById(R.id.shimmer_layout);
        layout.startShimmerAnimation();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.stopShimmerAnimation();
                layout.setVisibility(View.GONE);
            }
        }, 3000);
        getData(1, 15);
        super.onViewCreated(view, savedInstanceState);
    }

    private void getData(int table, int page) {
//        final String url = BASEURL+TABLE_NUM+table+PAGE+page;
//        protocal.loadData(url,handler);
        ViseHttp.GET("toutiao/index?type=top&key=00f741733cfe831eefdeec40274a2484")
                .setLocalCache(true)
                .cacheMode(CacheMode.FIRST_CACHE)
                .request(new ACallback<CacheResult<JuHeNewsBean>>() {
                    @Override
                    public void onSuccess(CacheResult<JuHeNewsBean> cacheResult) {
                        ViseLog.i("request onSuccess!"+Thread.currentThread().getName());

                        if (cacheResult == null || cacheResult.getCacheData() == null) {
                            return;
                        }
                        if (cacheResult.isCache()) {
                            ViseLog.i("From Cache:\n" + cacheResult.getCacheData().toString());
                            fillData(cacheResult.getCacheData().getResult().getData());
                        } else {
                            ViseLog.i("From Remote:\n" + cacheResult.getCacheData().toString());
                            fillData(cacheResult.getCacheData().getResult().getData());
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });


    }

    private void fillData(List<JuHeNewsBean.Data> data) {
        result = data;
        adapter = new HomeRycleAdapter(mActivity, result);

        adapter.setOnItemListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {

                Toast.makeText(mActivity, "被点的是position" + position, Toast.LENGTH_SHORT).show();

            }
        });
        m_recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        m_recyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
        m_recyclerView.setAdapter(adapter);

    }


    //    private void getData(int page){
////        final String ul = BASEURL+KEY+PAGE+page+ROWS;
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(ul);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    con.setRequestMethod("GET");
//                    con.setConnectTimeout(5000);
//                    con.setReadTimeout(5000);
//                    int responseCode = con.getResponseCode();
//                    StringBuilder builder = new StringBuilder();
//                    String json=null;
//                    if (responseCode == 200) {
//                        InputStream inputStream = con.getInputStream();
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//                        while ((json = reader.readLine())
//                                !=null) {
//                            builder.append(json);
//                        }
//
//                        json =  builder.toString();
//                        ;
//                    }
//
//
//                } catch (MalformedURLException e) {
//
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                super.run();
//            }
//        }.start();
//    }
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(mActivity, R.layout.fragment_important, null);

        return view;
    }


    @Override
    public void onRefresh() {
//        new AsyncTask<Void, Void, Void>(){
//
//            @Override
//            protected Void doInBackground(Void... params) {
//
//
//                return null;
//            }
//        }.execute();
//        getData(1, 15);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                    refreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
