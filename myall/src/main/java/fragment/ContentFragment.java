package fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myall.R;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class ContentFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    //@ViewInject(R.id.rb1)
//    @Bind(R.id.rb1)


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.main_content_fragment, null);
        //ViewUtils.inject(view);
        try {
//            ButterKnife.bind(view);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    @Override
    public void onClick(View v) {


    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //TODO
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
