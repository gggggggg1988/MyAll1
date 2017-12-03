package fragment.homefragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.myall.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import adapter.ArmyFragAdapter;
import entity.Data;
import fragment.BaseFragment;

import static util.Consts.BASEURL;
import static util.Consts.PAGE;
import static util.Consts.TABLE_NUM;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArmyFragment extends BaseFragment {
    private List<Data> temp = new ArrayList<>();
    private SparseArray m_array = new SparseArray();
    private ArmyFragAdapter adapter;
    private List<Data> result = new ArrayList<>();
    public static final int []RES = new int[]{R.mipmap.image5,R.mipmap.image2,R.mipmap.image3,R.mipmap.image4,R.mipmap.image6,R.mipmap.image7,R.mipmap.image8};
    public static final int []BANNER = new int[]{R.mipmap.banner1,R.mipmap.banner2,R.mipmap.banner3,R.mipmap.banner4,R.mipmap.banner5};
    private MZBannerView header;


    public ArmyFragment() {
        // Required empty public constructor

    }

    @Override
    protected void initData() {
        super.initData();
        getData(2, 1);
        adapter = new ArmyFragAdapter(mActivity, result);
        rv.setAdapter(adapter);
        rv.refresh();
    }

    private void getData(int table, int page) {
        final String url = BASEURL + TABLE_NUM + table + PAGE + page;
//        protocal.loadData(url,handler);
        result.clear();
        for (int i = 0; i < 10 * page; i++) {
            result.add(new Data("table:" + table + "title:" + "liwei" + i + "page:" + page));
        }
//        result.addAll(temp);
    }

    private XRecyclerView rv;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = (XRecyclerView) view.findViewById(R.id.army_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mActivity);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayout.VERTICAL));

        View header_layout = LayoutInflater.from(getActivity()).inflate(R.layout.header_layout, null);
         header = (MZBannerView) header_layout.findViewById(R.id.banner);
        header.setIndicatorVisible(true);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<RES.length;i++){
            list.add(RES[i]);
        }
        header.setPages(list, new MZHolderCreator<HeaderViewHolder>() {
            @Override
            public HeaderViewHolder createViewHolder() {
                return new HeaderViewHolder();
            }
        });
        LinearLayout parent = (LinearLayout) header.getParent();
        parent.removeView(header);
        View view1 = LayoutInflater.from(mActivity).inflate(R.layout.recyclerview_header, (ViewGroup) mActivity.findViewById(android.R.id.content), false);
        rv.addHeaderView(header);


        rv.setArrowImageView(R.drawable.iconfont_downgrey);
        rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            public int count = 1;

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        getData(3, 1);
                        adapter.notifyDataSetChanged();
                        rv.refreshComplete();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                count++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(3,count);
                        adapter.notifyDataSetChanged();
                rv.loadMoreComplete();
                    }
                },1000);

            }
        });
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_army, container, false);
    }
    class HeaderViewHolder implements MZViewHolder<Integer> {

        ImageView img;
        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            img = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            img.setImageResource(data);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        header.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        header.start();
    }
}
