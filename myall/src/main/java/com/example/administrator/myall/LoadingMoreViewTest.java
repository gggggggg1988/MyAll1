package com.example.administrator.myall;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.ArmyFragAdapter;
import entity.Data;

import static util.Consts.BASEURL;
import static util.Consts.PAGE;
import static util.Consts.TABLE_NUM;

public class LoadingMoreViewTest extends AppCompatActivity {
    private ArmyFragAdapter adapter;
    private List<Data> result = new ArrayList<>();
    private XRecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_more_view_test);

        rv = (XRecyclerView) findViewById(R.id.act_test_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        View view1 = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
        rv.addHeaderView(view1);


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

        getData(2, 1);
        adapter = new ArmyFragAdapter(this, result);
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
}
