package adapter.delegates;

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
import com.jcodecraeer.xrecyclerview.ItemViewDelegate;
import com.jcodecraeer.xrecyclerview.ViewHolder;

import java.io.File;

import fragment.PluginManager;

/**
 * Created by cantian on 2018/1/23.
 */

public class FuncListDelegate implements ItemViewDelegate<String> {
    private Context m_context;

    public FuncListDelegate(Context context) {
        m_context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout;
    }

    @Override
    public boolean isForViewType(String item, int position) {
        return true;
    }

    @Override
    public void convert(ViewHolder holder, String s, final int position) {
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
