package fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myall.R;

/**
 * Created by Administrator on 2016/5/3 0003.
 */
public class ContentFragment extends BaseFragment {


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.main_content_fragment,null);
        return view;
    }


}
