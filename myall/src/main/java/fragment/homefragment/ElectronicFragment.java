package fragment.homefragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myall.R;

import java.util.ArrayList;

import fragment.BaseFragment;
import fragment.homefragment.activitys.CoordinateLayoutActivity;
import fragment.homefragment.adapter.SimpleDataAdapter;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.moduleView.ModuleView;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.moduleView.ModuleViewBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElectronicFragment extends BaseFragment {

    private ModuleView m_moduleView;
    private ArrayList<ModuleViewBean> list;
    public ElectronicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        m_moduleView = view.findViewById(R.id.module_view);
        m_moduleView.isShowTitle(false);
        list = new ArrayList<>();
        list.add(new ModuleViewBean(null, "CoordinateLayout Behavior",null,CoordinateLayoutActivity.class));
        list.add(new ModuleViewBean(null, "探探view",null,null));
        list.add(new ModuleViewBean(null, "test2",null,null));
        m_moduleView.showRecycelerView(getActivity(), list, new SimpleDataAdapter(getActivity()));
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_electronic, container, false);
    }


}
