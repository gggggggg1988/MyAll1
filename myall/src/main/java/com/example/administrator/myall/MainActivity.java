package com.example.administrator.myall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import fragment.BaseFragment;
import fragment.ContentFragment;
import fragment.HomeFragment;
import fragment.MeFragment;
import fragment.PicFragment;
import fragment.VedeoFragment;
import util.DimentionUtil;
import util.LoginApi;
import util.OnLoginListener;
import util.UserInfo;
import widget.CircleView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG_CONTENT = "content";
    private static final String TAG_MENU = "menu";
    Button m_btn_home;
    //@ViewInject(R.id.rb2)
//    @Bind(R.id.rb2)
    Button m_btn_me;
    //@ViewInject(R.id.rb3)
//    @Bind(R.id.rb3)
    Button m_btnVieo;
    //@ViewInject(R.id.rb4)
//    @Bind(R.id.rb4)
    Button m_btnPic;
    //    @Bind(R.id.imageView2)//
    //@ViewInject(R.id.imageView2)
    ImageView rightIv;
    CircleView circleView;
    //@ViewInject(R.id.home_viewpager)
//    @Bind(R.id.home_viewpager)

    private ViewPager homeViewpager;
    private Button[] btns = new Button[4];
    private List<BaseFragment> list = new ArrayList<BaseFragment>();
    private PopupWindow pop;
    private int ivHight;
    private int popHeight, popWidth;
    private FrameLayout container;
    private Fragment main, vedio, pic, me;
    private View menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        setContentView(R.layout.activity_main);//第一层加载的主视图，也是主容器
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
//        Toast.makeText(this, "MyJobService", Toast.LENGTH_SHORT).show();
        setBehindContentView(R.layout.main_menu_layout);//第一层加载的主menu，也是menu容器
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindWidth(DimentionUtil.dpi2Px(this, 250));
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBackgroundColor(getResources().getColor(R.color.colorMenu));
        m_btn_home = (Button) findViewById(R.id.rb1);
        m_btnVieo = (Button) findViewById(R.id.rb2);
        m_btnPic = (Button) findViewById(R.id.rb3);
        m_btn_me = (Button) findViewById(R.id.rb4);
        rightIv = (ImageView) findViewById(R.id.imageView2);
        container = (FrameLayout) findViewById(R.id.content_container);
        // homeViewpager = (ViewPager) findViewById(R.id.home_viewpager);
        btns[0] = m_btn_home;
        btns[1] = m_btnVieo;
        btns[2] = m_btnPic;
        btns[3] = m_btn_me;
        m_btn_home.setSelected(true);


        m_btn_home.setOnClickListener(this);
        m_btn_me.setOnClickListener(this);
        m_btnVieo.setOnClickListener(this);
        m_btnPic.setOnClickListener(this);
        rightIv.setOnClickListener(this);
//        list.add(new HomeFragment());
//        list.add(new VedeoFragment());
//        list.add(new PicFragment());
//        list.add(new MeFragment());
//        homeViewpager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
//        homeViewpager.addOnPageChangeListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        main = new HomeFragment();
        transaction.add(R.id.content_container, main, "frag");
        transaction.show(main);
        transaction.commit();

        currentFrag = main;
        initViews();
    }



    private void initViews() {
        menu = View.inflate(this, R.layout.main_menu_layout, null);
        circleView = (CircleView) menu.findViewById(R.id.circleView);
        circleView.setOnClickListener(this);
        setMenuContent();
    }

private void setMenuContent() {

    new Thread(){
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ArrayList<String> items = new ArrayList<String>();
            items.add("国内新闻");
            items.add("科技新闻");
            items.add("农业新闻");
            items.add("军事新闻");
            items.add("国际新闻");
            EventBus.getDefault().post(items);
        }
    }.start();



}

    /**
     * // Called in the same thread (default)

     @Subscribe(threadMode = ThreadMode.POSTING) // ThreadMode is optional here
     public void onMessage(MessageEvent event) {
     log(event.message);
     }

     // Called in Android UI's main thread
     @Subscribe(threadMode = ThreadMode.MAIN)
     public void onMessage(MessageEvent event) {
     textField.setText(event.message);
     }

     // Called in the background thread
     @Subscribe(threadMode = ThreadMode.BACKGROUND)
     public void onMessage(MessageEvent event){
     saveToDisk(event.message);
     }

     // Called in a separate thread
     @Subscribe(threadMode = ThreadMode.ASYNC)
     public void onMessage(MessageEvent event){
     backend.send(event.message);
     }
     * @param list
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent (ArrayList<String> list) {
        ListView listView = (ListView) findViewById(R.id.listMenu);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
    }

    public ContentFragment getContentFragment() {
        FragmentManager manager = getSupportFragmentManager();
        return (ContentFragment) manager.findFragmentByTag(TAG_CONTENT);
    }

    private Fragment currentFrag;

    public void switchFragment(Fragment current, Fragment to) {
        if (current != to) {

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.hide(current).commit();

            currentFrag = to;
            if (to.isAdded()) {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).show(to).commit();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.content_container, to, "frag").setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).show(to).commit();
            }
        } else {
            return;
        }
    }
 /*   public void switchFrag(Fragment from,Fragment to){
        if (from != to) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).hide(from).commit();
            currentFrag = to;
            if (to.isAdded()) {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).show(to).commit();
            }else {
                getSupportFragmentManager().beginTransaction().add(R.id.content_container,to,"to").setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).show(to).commit();
            }
        }else{
            return;
        }
    }*/
    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.rb1:
                if (main == null) {
                    main = new HomeFragment();
                }
                //currentFrag = getSupportFragmentManager().findFragmentByTag("frag");
                switchFragment(currentFrag, main);
                setPressed(0);
                break;
            case R.id.rb2:
                if (vedio == null) {
                    vedio = new VedeoFragment();
                }
                // currentFrag = getSupportFragmentManager().findFragmentByTag("frag");
                switchFragment(currentFrag, vedio);
                setPressed(1);
                break;
            case R.id.rb3:
                if (pic == null) {
                    pic = new PicFragment();
                }
                // currentFrag = getSupportFragmentManager().findFragmentByTag("frag");
                switchFragment(currentFrag, pic);
                setPressed(2);
                break;
            case R.id.rb4:
                if (me == null) {
                    me = new MeFragment();
                }
                //currentFrag = getSupportFragmentManager().findFragmentByTag("frag");
                switchFragment(currentFrag, me);
                setPressed(3);
                break;
            case R.id.circleView:
               login("QQ");
                break;
            case R.id.imageView2:

                if (pop == null) {
                    View popLayout = View.inflate(this, R.layout.popup_window_layout, null);
                    popLayout.findViewById(R.id.popButton1).setOnClickListener(this);
                    popLayout.findViewById(R.id.popButton2).setOnClickListener(this);
                    popLayout.findViewById(R.id.popButton3).setOnClickListener(this);
                    pop = new PopupWindow(popLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    ivHight = rightIv.getHeight();
                    popHeight = popLayout.getMeasuredHeight();
                    popWidth = popLayout.getMeasuredWidth();


                    pop.showAsDropDown(rightIv, -popWidth, ivHight);
                } else {
                    if (pop.isShowing()) {
                        pop.dismiss();
                    } else {

                        pop.showAsDropDown(rightIv, -popWidth, ivHight);
                    }
                }


                break;
            case R.id.popButton1:
                pop.dismiss();
                break;
            case R.id.popButton2:
                pop.dismiss();
                //TODO implement
                break;
            case R.id.popButton3:
                pop.dismiss();
                //TODO implement
                break;
            default:
                break;
        }
    }

    private void setPressed(int currentIndex) {
        for (int i = 0; i < btns.length; i++) {
            btns[i].setSelected(false);
        }
        btns[currentIndex].setSelected(true);
    }



//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }




    /*
 * 演示执行第三方登录/注册的方法
 * <p>
 * 这不是一个完整的示例代码，需要根据您项目的业务需求，改写登录/注册回调函数
 *
 * @param platformName 执行登录/注册的平台名称，如：SinaWeibo.NAME
 */
    private void login(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platform, HashMap<String, Object> res) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                return true;
            }

            public boolean onRegister(UserInfo info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        api.login(this);
    }
}
