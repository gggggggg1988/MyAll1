package ndkjnidemo.wobiancao.com.recyclerviewstudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ndkjnidemo.wobiancao.com.recyclerviewstudy.R;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.RecyclerViewAdapter;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.StickyRecyclerView;

public class MainActivity extends FragmentActivity {
    private StickyRecyclerView m_recyclerView;
    private List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_recyclerView = (StickyRecyclerView) findViewById(R.id.rv);
//        m_recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        m_recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        initData();
        m_recyclerView.setAdapter(new RecyclerViewAdapter(data));
//        m_recyclerView.addItemDecoration(new ItemDivider().setDividerWith(4).setDividerColor(Color.GREEN));

    }
    public void doClick(View view){
        Intent i = new Intent(this, AnotherRecyclerView.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add("content:" + i);
        }
    }
}
