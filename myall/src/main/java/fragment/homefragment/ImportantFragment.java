package fragment.homefragment;


import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
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
import utils.NotificationUtils;
import widget.DividerLine;

import static android.app.Notification.VISIBILITY_SECRET;

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
    public static final int []RES = new int[]{R.mipmap.image5,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image6,R.mipmap.image7,R.mipmap.image8};
    public static final int []BANNER = new int[]{R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5};

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
    private NotificationManager mNManager;
    private Notification notify1;
    private int NOTIFYID_1=100;
    private NotificationManager manager;


    public ImportantFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

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


//        m_recyclerView.addView(header,0);
        m_recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (adapter!=null) {
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
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = m_layoutManager.findLastVisibleItemPosition();
            }
        });
        DividerLine divider = new DividerLine(LinearLayoutManager.VERTICAL);
        divider.setSize(2);
        divider.setColor(Color.RED);
        m_recyclerView.addItemDecoration(divider);

//        getData(1);
        // Required empty public constructor
        layout = (ShimmerLayout) view.findViewById(R.id.shimmer_layout);
        layout.startShimmerAnimation();

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
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(CacheResult<JuHeNewsBean> cacheResult) {
                        ViseLog.i("request onSuccess!"+Thread.currentThread().getName());
                        layout.stopShimmerAnimation();
                        layout.setVisibility(View.GONE);
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

//                        alertTip();

//                        notice();
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        layout.stopShimmerAnimation();
                        layout.setVisibility(View.GONE);
                    }
                });


    }

    private NotificationManager getManager(){
        if(manager == null){
            manager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notice() {
        final Notification.Builder builder = getNotificationBuilder();
        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
        getManager().notify(2,builder.build());


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    try{
                        Thread.sleep(1000);

                        builder.setOnlyAlertOnce(true);
                        builder.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);
                        builder.setProgress(100,i,false);
                        getManager().notify(2,builder.build());
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateChannel(int i) {
        getManager().deleteNotificationChannel("channel_id"+i);
        //id随便指定
        i++;
        NotificationChannel channel = new NotificationChannel("channel_id"+i,"channel_name"+i, NotificationManager.IMPORTANCE_LOW);
        channel.canBypassDnd();//可否绕过，请勿打扰模式
        channel.enableLights(true);//闪光
        channel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
        channel.setLightColor(Color.RED);//指定闪光是的灯光颜色
        channel.canShowBadge();//桌面laucher消息角标
        channel.enableVibration(false);//是否允许震动
        channel.getAudioAttributes();//获取系统通知响铃声音配置
        channel.getGroup();//获取通知渠道组
        channel.setBypassDnd(true);//设置可以绕过，请勿打扰模式
        channel.setVibrationPattern(new long[]{0});//震动的模式，震3次，第一次100，第二次100，第三次200毫秒
        channel.shouldShowLights();//是否会闪光
        //通知管理者创建的渠道
        getManager().createNotificationChannel(channel);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification.Builder getNotificationBuilder(){
        //大于8.0
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //id随便指定
            NotificationChannel channel = new NotificationChannel("channel_id","channel_name", NotificationManager.IMPORTANCE_LOW);
            channel.canBypassDnd();//可否绕过，请勿打扰模式
            channel.enableLights(true);//闪光
            channel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
            channel.setLightColor(Color.RED);//指定闪光是的灯光颜色
            channel.canShowBadge();//桌面laucher消息角标
            channel.enableVibration(true);//是否允许震动
            channel.getAudioAttributes();//获取系统通知响铃声音配置
            channel.getGroup();//获取通知渠道组
            channel.setBypassDnd(true);//设置可以绕过，请勿打扰模式
            channel.setVibrationPattern(new long[]{100,100,200});//震动的模式，震3次，第一次100，第二次100，第三次200毫秒
            channel.shouldShowLights();//是否会闪光
            //通知管理者创建的渠道
            getManager().createNotificationChannel(channel);

        }
        return new Notification.Builder(getActivity()).setAutoCancel(true).setChannelId("channel_id")
                .setContentTitle("新消息来了")
                .setContentText("新消息内容").setSmallIcon(R.mipmap.ic_launcher);
    }


    private void alertTip() {
        String channelID = "1";

        String channelName = "channel_name";
//        Intent it = new Intent(getActivity(), MainActivity.class);
//        PendingIntent pit = PendingIntent.getActivity(mActivity, 0, it, 0);
//        NotificationUtils.getInstance(mActivity).sendNotification("通知","列表加载完成...",R.mipmap.batman,pit);
        final NotificationUtils instance = NotificationUtils.getInstance(mActivity);
        instance.createDownloadNotification();
        new Thread(){
            int i = 0;
            @Override
            public void run() {
                super.run();

                for(i=0;i<100;i++){
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                instance.setProgress(i);
                            }
                        });

                        try {
                                Thread.sleep(500);//演示休眠50毫秒
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                    }

                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            instance.setProgress(i);
                        }
                    });
            }
        }.start();

//        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

//        mNManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
//        //定义一个PendingIntent点击Notification后启动一个Activity
//
//        //设置图片,通知标题,发送时间,提示方式等属性
//        Notification.Builder mBuilder = new Notification.Builder(mActivity);
//        mBuilder.setContentTitle("叶良辰")                        //标题
//                .setContentText("我有一百种方法让你呆不下去~")      //内容
//                .setSubText("——记住我叫叶良辰")                    //内容下面的一小段文字
//                .setTicker("收到叶良辰发送过来的信息~")             //收到信息后状态栏显示的文字信息
//                .setWhen(System.currentTimeMillis())           //设置通知时间
//                .setSmallIcon(R.mipmap.ic_launcher)            //设置小图标
//                .setLargeIcon(BitmapFactory.decodeResource(mActivity.getResources(),R.mipmap.batman))                     //设置大图标
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)    //设置默认的三色灯与振动器
////                .setSound(Uri.parse("android.resource://" + mActivity.getPackageName() + "/" + R.raw.biaobiao))  //设置自定义的提示音
//                .setAutoCancel(true)                           //设置点击后取消Notification
//                .setContentIntent(pit);                        //设置PendingIntent
//        notify1 = mBuilder.build();
//        mNManager.notify(NOTIFYID_1, notify1);
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
