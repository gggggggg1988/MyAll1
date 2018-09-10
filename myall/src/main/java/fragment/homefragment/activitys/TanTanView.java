package fragment.homefragment.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.administrator.myall.R;

import java.util.ArrayList;
import java.util.List;

import fragment.homefragment.adapter.TanTanViewAdapter;
import fragment.homefragment.bean.TanTanViewBean;
import fragment.homefragment.biz.CardConfig;
import fragment.homefragment.biz.CardViewCallBack;
import fragment.homefragment.biz.SwipeCardLayoutManager;

public class TanTanView extends AppCompatActivity {
    private RecyclerView m_recyclerView;
    private List<TanTanViewBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tan_tan_view);
        CardConfig.initConfig(this);
        m_recyclerView = (RecyclerView) findViewById(R.id.rv);
        m_recyclerView.setLayoutManager(new SwipeCardLayoutManager());
        data = new ArrayList<>();
        data.add(new TanTanViewBean("meinv",R.mipmap.girl));
        data.add(new TanTanViewBean("meinv1",R.mipmap.banner1));
        data.add(new TanTanViewBean("meinv2",R.mipmap.banner2));
        data.add(new TanTanViewBean("meinv3",R.mipmap.banner3));
        data.add(new TanTanViewBean("meinv4",R.mipmap.banner4));
        TanTanViewAdapter adapter = new TanTanViewAdapter(this,R.layout.tantan_view_item,data);
        m_recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new CardViewCallBack(m_recyclerView,adapter,data);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(m_recyclerView);

    }
}
