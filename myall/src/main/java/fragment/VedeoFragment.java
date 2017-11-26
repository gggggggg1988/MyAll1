package fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myall.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.FunctionListAdapter;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VedeoFragment extends BaseFragment {
    private List data = new ArrayList();
private  XRecyclerView  m_xRecyclerView;
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_vedeo, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        m_xRecyclerView = (XRecyclerView) view.findViewById(R.id.xrv);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(mActivity);
        m_xRecyclerView.setLayoutManager(lm);
        getData();
        m_xRecyclerView.setAdapter(new FunctionListAdapter(mActivity,R.layout.layout,data));
        m_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                m_xRecyclerView.loadMoreComplete();
            }
        });
    }

    private void getData() {
        data.add("StartPlugin");
        data.add("AgentWebviewTest");
        data.add("一像素保活");
        data.add("双进程守护");
        data.add("RandomRecommand");
        data.add("魅族banner");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



//    @OnClick(R.id.plugin) void onPluginClick() {
//
//        PluginManager.getInstance().init(mActivity);
//
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//        File file = new File(Environment.getExternalStorageDirectory(), "biaobai-debug.apk");
//        PluginManager.getInstance().loadApk(file.getAbsolutePath());
//
//
//        Intent in = new Intent(mActivity, ProxyActivity.class);
//        PackageInfo info = PluginManager.getInstance().getPackageInfo();
//        ActivityInfo[] activities = info.activities;
//        in.putExtra("className", activities[0].name);
//
//        startActivity(in);
//    }


}
