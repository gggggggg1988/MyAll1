package ndkjnidemo.wobiancao.com.recyclerviewstudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ndkjnidemo.wobiancao.com.recyclerviewstudy.R;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.RecyclerViewAdapter1;

public class AnotherRecyclerView extends AppCompatActivity implements View.OnClickListener {
    private  RecyclerView view;
    private List<String> data;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_recycler_view);
        view = (RecyclerView) findViewById(R.id.rv1);
        btn = (Button) findViewById(R.id.btn_go_on);
        btn.setOnClickListener(this);
        initData();;
        GridLayoutManager.SpanSizeLookup lookup = new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (position<9 ) {
                    return 1;
                }else {

                return 3;}
            }
        };
        GridLayoutManager manager = new GridLayoutManager(this,3);
        manager.setSpanSizeLookup(lookup);

        view.setLayoutManager(manager);
        view.setAdapter(new RecyclerViewAdapter1(data));
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add("content:" + i);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, LoadMoreActivity.class);
        startActivity(i);
    }
}
