package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myall.R;

public class SearchResultAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
    }

//    public void doSearchQuery(){
//        final Intent intent = getIntent();
//        //获得搜索框里值
//        String query=intent.getStringExtra(SearchManager.QUERY);
//        tvquery.setText(query);
//        //保存搜索记录
//        SearchRecentSuggestions suggestions=new SearchRecentSuggestions(this,
//                SearchSuggestionSampleProvider.AUTHORITY, SearchSuggestionSampleProvider.MODE);
//        suggestions.saveRecentQuery(query, null);
//        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
//            //获取传递的数据
//            Bundle bundled=intent.getBundleExtra(SearchManager.APP_DATA);
//            if(bundled!=null){
//                String ttdata=bundled.getString("data");
//                tvdata.setText(ttdata);
//
//            }else{
//                tvdata.setText("no data");
//            }
//        }
//    }
}
