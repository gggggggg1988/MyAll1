package com.example.administrator.myall.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myall.R;
import com.example.administrator.myall.widget.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class CoordinateLayoutDemo extends AppCompatActivity {

    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate_layout_demo);
        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        bar.setTitle("Demo");
        setSupportActionBar(bar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("data liwei"+i);
        }
        recyclerView.setAdapter(new Xadapter(data,this));
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
         TextView m_textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            m_textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
    class Xadapter extends RecyclerView.Adapter{
       private List<String> data;
        private Context m_context;

        public Xadapter(List<String> data, Context context) {
            this.data = data;
            m_context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(m_context).inflate(android.R.layout.simple_list_item_1, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.m_textView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
