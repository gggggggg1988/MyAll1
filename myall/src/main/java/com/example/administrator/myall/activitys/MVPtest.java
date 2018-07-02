package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myall.MVPBaseAct;
import com.example.administrator.myall.R;
import com.example.administrator.myall.presenter.GirlPresenter;
import com.example.administrator.myall.view.IGirlView;

import java.util.List;

import entity.Data;

public class MVPtest extends MVPBaseAct<IGirlView,GirlPresenter<IGirlView>> implements IGirlView{
private RecyclerView m_recyclerView ;
    private RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);
        m_recyclerView = (RecyclerView) findViewById(R.id.rv);
        presenter.fetch();
    }

    @Override
    public GirlPresenter<IGirlView> initPresenter() {
        return new GirlPresenter<>();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showGirls(List<Data> girls) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(manager);
        adapter = new Mvpadapter(girls);
        m_recyclerView.setAdapter(adapter);
    }

    class Mvpadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        List<Data> m_datas ;

        class MvpViewholder extends RecyclerView.ViewHolder{
            public TextView view;
            public MvpViewholder(View itemView) {
                super(itemView);
                view = (TextView) itemView.findViewById(android.R.id.text1);
            }


        }

        public Mvpadapter(List<Data> datas) {

            m_datas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);
            return new MvpViewholder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MvpViewholder hold = (MvpViewholder) holder;
            hold.view.setText(m_datas.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return m_datas.size();
        }
    }
}
