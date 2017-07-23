package fragment.homefragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.example.administrator.myall.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import MyInterface.RecyclerItemClickListener;
import adapter.HomeRycleAdapter;
import entity.Data;
import entity.News;
import fragment.BaseFragment;
import http.BaseProtocal;
import http.NewsProtocal;
import widget.BannerView;
import widget.footer.LoadMoreFooterView;

import static util.Consts.BASEURL;
import static util.Consts.PAGE;
import static util.Consts.TABLE_NUM;


/**
 * A simple {@link Fragment} subclass.
 */
public class TechFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {


    private IRecyclerView iRecyclerView;
    private BannerView bannerView;
    private LoadMoreFooterView loadMoreFooterView;
    private List<Data> result= new ArrayList<>();
    private List<Data> temp= new ArrayList<>();
    private HomeRycleAdapter adapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0 ) {

                String JSON = (String) msg.obj;
                Gson gson = new Gson();
                News news = gson.fromJson(JSON, News.class);
                result = news.getData();
                adapter.notifyDataSetChanged();
                iRecyclerView.setRefreshing(false);
            }
        }
    };
    private BaseProtocal protocal = new NewsProtocal();
    private int page;

    public TechFragment() {
        // Required empty public constructor
    }
    private void getData(int table,int page) {
        final String url = BASEURL+TABLE_NUM+table+PAGE+page;
//        protocal.loadData(url,handler);
        temp.clear();
        for (int i = 0; i < 10; i++) {
            temp.add(new Data("table:"+table+"title:"+"liwei"+i+"page:"+page));
        }
        result.addAll(temp);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iRecyclerView = (IRecyclerView) mActivity.findViewById(R.id.iRecyclerView);
        iRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        bannerView = (BannerView) LayoutInflater.from(mActivity).inflate(R.layout.layout_banner_view, iRecyclerView.getHeaderContainer(), false);
        iRecyclerView.addHeaderView(bannerView);

        loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
        adapter = new HomeRycleAdapter(mActivity,result==null?new ArrayList<Data>():result);

        adapter.setOnItemListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(mActivity, "被点的是position" + position, Toast.LENGTH_SHORT).show();

            }
        });
        getData(2,1);
        iRecyclerView.setAdapter(adapter);

        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);


        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(true);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_tech, container, false);
    }


    @Override
    public void onRefresh() {
        getData(2,1);
        adapter.notifyDataSetChanged();
        iRecyclerView.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        page++;
        getData(2,page);
        adapter.notifyDataSetChanged();

    }
}
