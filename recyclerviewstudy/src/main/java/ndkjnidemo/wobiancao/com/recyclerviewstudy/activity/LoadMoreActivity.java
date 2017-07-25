package ndkjnidemo.wobiancao.com.recyclerviewstudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import ndkjnidemo.wobiancao.com.recyclerviewstudy.ItemTouchHelperCallback;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.R;
import ndkjnidemo.wobiancao.com.recyclerviewstudy.RecyclerViewAdapter1;

public class LoadMoreActivity extends AppCompatActivity {
    private RecyclerView view;
    private List<String> data;
    private ItemTouchHelper.Callback itemTouchHelperCallback;
    private RecyclerViewAdapter1 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);
        view = (RecyclerView) findViewById(R.id.rv2);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        view.setLayoutManager(manager);
        adapter=new RecyclerViewAdapter1(data);
        view.setAdapter(adapter);
        itemTouchHelperCallback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(itemTouchHelperCallback);
        helper.attachToRecyclerView(view);

    }
    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            data.add("content:" + i);
        }
    }
}
