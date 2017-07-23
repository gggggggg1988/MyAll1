package fragment.homefragment;


import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myall.R;

import fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeoplesFragment extends BaseFragment {


    public PeoplesFragment() {
        // Required empty public constructor
    }




    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_peoples, container, false);
    }


}
