package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myall.R;

public class AlertBounceView extends AppCompatActivity implements View.OnClickListener {
   private TextView m_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_bounce_view);
        m_textView = (TextView) findViewById(R.id.textView8);

        m_textView.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bouce_view_options, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
        View actionView = item.getActionView();
        if (id==R.id.go_alert ) {
            View view = LayoutInflater.from(this).inflate(R.layout.bounce_view_layout, null, false);
            MenuBar.make(m_textView,R.layout.bounce_view_layout).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                     case R.id.textView8:
                         MenuBar.make(m_textView,R.layout.bounce_view_layout).show();
                         break;

                     default:
                         break;
                   }

    }
}
