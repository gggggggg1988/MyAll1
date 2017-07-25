package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myall.AgentWebviewTest;
import com.example.administrator.myall.ProxyActivity;
import com.example.administrator.myall.R;

import java.io.File;
import java.util.List;

import fragment.PluginManager;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.CommonAdapter;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class FunctionListAdapter extends CommonAdapter<String> {
    private Context m_context;

    public FunctionListAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        m_context = context;
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        Button btn = holder.getView(R.id.StartPlugin);
        btn.setText(s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 1:
                        PluginManager.getInstance().init(m_context);

                        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                        File file = new File(Environment.getExternalStorageDirectory(), "biaobai-debug.apk");
                        PluginManager.getInstance().loadApk(file.getAbsolutePath());


                        Intent in = new Intent(m_context, ProxyActivity.class);
                        PackageInfo info = PluginManager.getInstance().getPackageInfo();
                        ActivityInfo[] activities = info.activities;
                        in.putExtra("className", activities[0].name);

                        m_context.startActivity(in);
                        break;
                    case 2:
                        Intent intent = new Intent(m_context, AgentWebviewTest.class);
                        m_context.startActivity(intent);
                        break;

                    default:
                        break;
                }

            }
        });
    }
}
