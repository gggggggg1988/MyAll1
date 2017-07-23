package ndkjnidemo.wobiancao.com.recyclerviewstudy.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ndkjnidemo.wobiancao.com.recyclerviewstudy.ItemDivider;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.R;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.RecyclerViewAdapter;

public class MainActivity1 extends FragmentActivity {
    private RecyclerView m_recyclerView;
    private List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_recyclerView = (RecyclerView) findViewById(R.id.rv);
//        m_recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        m_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        initData();
        m_recyclerView.setAdapter(new RecyclerViewAdapter(data));
        m_recyclerView.addItemDecoration(new ItemDivider().setDividerWith(4).setDividerColor(Color.GREEN));

    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add("content:" + i);
        }
    }
}
