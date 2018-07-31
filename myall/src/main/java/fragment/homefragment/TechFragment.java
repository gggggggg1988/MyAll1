package fragment.homefragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myall.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import MyInterface.RecyclerItemClickListener;
import adapter.DemoRycleAdapter;
import entity.Data;
import fragment.BaseFragment;
import http.BaseProtocal;
import http.NewsProtocal;
import widget.BannerView;
import widget.footer.LoadMoreFooterView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechFragment extends BaseFragment  {


    private XRecyclerView iRecyclerView;
    private BannerView bannerView;
    private LoadMoreFooterView loadMoreFooterView;
    private List<Data> result= new ArrayList<>();
    private List<Data> temp= new ArrayList<>();
    private DemoRycleAdapter adapter;
    private BaseProtocal protocal = new NewsProtocal();
    private int page;

    public TechFragment() {
        // Required empty public constructor
    }
    private void getData() {
//        final String url = BASEURL+TABLE_NUM+table+PAGE+page;
//        protocal.loadData(url,handler);
        temp.clear();


        temp.add(new Data("使用jobScheduler保活demo"));
        temp.add(new Data("retrofit使用demo"));
        temp.add(new Data("粘性下拉控件"));
        temp.add(new Data("沉浸式状态栏"));
        temp.add(new Data("xfermode圆形头像"));



        result.addAll(temp);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iRecyclerView = (XRecyclerView) mActivity.findViewById(R.id.iRecyclerView);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

//        bannerView = (BannerView) LayoutInflater.from(mActivity).inflate(R.layout.layout_banner_view, iRecyclerView.getHeaderContainer(), false);
//        iRecyclerView.addHeaderView(bannerView);

//        loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
        adapter = new DemoRycleAdapter(mActivity,result==null?new ArrayList<Data>():result);

        adapter.setOnItemListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(mActivity, "被点的是position" + position, Toast.LENGTH_SHORT).show();

            }
        });
        getData();
        iRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tech, container, false);
    }



}
