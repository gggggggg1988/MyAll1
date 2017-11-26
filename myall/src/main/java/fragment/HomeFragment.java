package fragment;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.myall.R;

import java.util.ArrayList;
import java.util.List;

import fragment.homefragment.ArmyFragment;
import fragment.homefragment.ElectronicFragment;
import fragment.homefragment.ImportantFragment;
import fragment.homefragment.PeoplesFragment;
import fragment.homefragment.SocietyFragment;
import fragment.homefragment.TechFragment;

/**
 * 
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private PagerSlidingTabStrip indicator;
    private ViewPager homeViewPager;
    private List<BaseFragment> list = new ArrayList<>();
//    private String[] titles = {getResources().getString(R.string.important),getResources().getString(R.string.tech),getResources().getString(R.string.army)
//    ,getResources().getString(R.string.electronic),getResources().getString(R.string.people),getResources().getString(R.string.society)};
    private  String[] titles = {"要闻","科技","军事","经济","社会","人民"};

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.add(new ImportantFragment());
        list.add(new TechFragment());
        list.add(new ArmyFragment());//添加fragment数据集合，下面viewpager要用
        list.add(new ElectronicFragment());
        list.add(new SocietyFragment());
        list.add(new PeoplesFragment());

        indicator = (PagerSlidingTabStrip) view.findViewById(R.id.indicator);
        indicator.setTextColor(R.color.colorSelected);
        homeViewPager = (ViewPager) view.findViewById(R.id.home_view_pager);
        homeViewPager.setAdapter(new SubFragmentAdapter(((FragmentActivity) mActivity).getSupportFragmentManager()));
        indicator.setViewPager(homeViewPager);
        indicator.setOnPageChangeListener(this);

    }
    public HomeFragment() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class SubFragmentAdapter extends FragmentPagerAdapter {

        public SubFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 设置indicator的选项标题文字
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            if (position >5) {
                return " ";
            }
            return titles[position];
        }
    }
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
