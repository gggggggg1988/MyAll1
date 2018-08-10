package com.example.administrator.myall.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myall.R;

import java.util.ArrayList;
import java.util.List;

import utils.CommonPopupWindow;
import utils.MaterialDialogsUtil;
import utils.PopupWindowUtil;

public class PopupwindowAlertdialog extends AppCompatActivity implements View.OnClickListener {
    private Button btn_pop1, btn_pop2, alert, evalation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow_alertdialog);
        btn_pop1 = (Button) findViewById(R.id.popwindow_left);
        btn_pop2 = (Button) findViewById(R.id.popwindow_from_bottom);

        alert = (Button) findViewById(R.id.alert);
        evalation = (Button) findViewById(R.id.eval);

        btn_pop2.setOnClickListener(this);
        btn_pop1.setOnClickListener(this);
        alert.setOnClickListener(this);
        evalation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popwindow_from_bottom:
                List<PopupWindowUtil.PopSelectBean> list = new ArrayList<>();
                list.add(new PopupWindowUtil.PopSelectBean("确定"));
                list.add(new PopupWindowUtil.PopSelectBean("取消"));
                list.add(new PopupWindowUtil.PopSelectBean("很好"));
                PopupWindowUtil.getInstance().showSelectPop(this, list, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(PopupwindowAlertdialog.this, id + "", Toast.LENGTH_SHORT).show();
                        PopupWindowUtil.getInstance().closePopupWindow();
                    }
                });
                break;
            case R.id.popwindow_left:
                PopupWindowUtil.getInstance().showBottomPop(v, this, new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        Toast.makeText(PopupwindowAlertdialog.this, layoutResId + "" + view.toString(), Toast.LENGTH_SHORT).show();
                        PopupWindowUtil.getInstance().closePopupWindow();
                    }
                }, R.layout.pop);
                break;
            case R.id.alert:
                MaterialDialogsUtil.getInstance(this).createDuoXDialog().build().show();
                break;
            case R.id.eval:
                PopupWindowUtil.getInstance().showEvaluationPop(v, this, new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        Toast.makeText(PopupwindowAlertdialog.this, layoutResId + "" + view.toString(), Toast.LENGTH_SHORT).show();
                        PopupWindowUtil.getInstance().closePopupWindow();
                    }
                });
                break;

            default:
                break;
        }

    }
}
