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
import com.example.administrator.myall.activitys.AlertBounceView;
import com.example.administrator.myall.activitys.CoordinateLayoutDemo;
import com.example.administrator.myall.activitys.MzBanner;
import com.example.administrator.myall.activitys.RandomRecommand;
import com.example.administrator.myall.biz.OneDotKeepLiveService;
import com.example.administrator.myall.service.keepAliveService.JobHandleService;
import com.example.administrator.myall.service.keepAliveService.LocalService;
import com.example.administrator.myall.service.keepAliveService.RemoteService;

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
                    case 3:
                        Intent intent1 = new Intent(m_context, OneDotKeepLiveService.class);
                        m_context.startService(intent1);
                        break;
                    case 4:
                        Intent intent2 = new Intent(m_context, LocalService.class);
                        m_context.startService(intent2);
                        Intent inten3 = new Intent(m_context, RemoteService.class);
                        m_context.startService(inten3);
                        Intent inten4 = new Intent(m_context, JobHandleService.class);
                        m_context.startService(inten4);
                        break;
                    case 5:
                        Intent in5 = new Intent(m_context, RandomRecommand.class);
                        m_context.startActivity(in5);
                        break;
                    case 6:
                        Intent in6 = new Intent(m_context, MzBanner.class);
                        m_context.startActivity(in6);
                        break;
                    case 7:
                        Intent in7 = new Intent(m_context, CoordinateLayoutDemo.class);
                        m_context.startActivity(in7);
                        break;
                    case 8:
//                        Intent in8 = new Intent(m_context, CoordinateAndViewpager.class);
//                        m_context.startActivity(in8);
                        break;
                    case 9:
                        Intent in9 = new Intent(m_context, AlertBounceView.class);
                        m_context.startActivity(in9);
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
