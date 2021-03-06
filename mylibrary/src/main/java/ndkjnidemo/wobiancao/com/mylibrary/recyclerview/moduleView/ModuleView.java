package ndkjnidemo.wobiancao.com.mylibrary.recyclerview.moduleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ndkjnidemo.wobiancao.com.mylibrary.R;
import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.BaseRecyclerAdapter;



public class ModuleView extends LinearLayout {
   private TextView tv_title;
   private ImageView img_title,img_right;
   private RecyclerView recyclerView;
   private LinearLayout ll_right,ll_title;
   private BaseRecyclerAdapter adapter;

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
    }

    public ImageView getImg_title() {
        return img_title;
    }

    public void setImg_title(ImageView img_title) {
        this.img_title = img_title;
    }

    public ImageView getImg_right() {
        return img_right;
    }

    public void setImg_right(ImageView img_right) {
        this.img_right = img_right;
    }

    public LinearLayout getLl_right() {
        return ll_right;
    }

    public void setLl_right(LinearLayout ll_right) {
        this.ll_right = ll_right;
    }

    public LinearLayout getLl_title() {
        return ll_title;
    }

    public void setLl_title(LinearLayout ll_title) {
        this.ll_title = ll_title;
    }

    public BaseRecyclerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    public ModuleView(Context context) {
        this(context,null);
    }

    public ModuleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ModuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_moduleview,this);
        tv_title= (TextView) findViewById(R.id.view_moduleview_tv_title);
        img_title= (ImageView) findViewById(R.id.view_moduleview_img_title);
        img_right= (ImageView) findViewById(R.id.view_moduleview_img_rightimg);
        recyclerView= (RecyclerView) findViewById(R.id.view_moduleview_recyclerView);
        ll_right= (LinearLayout) findViewById(R.id.view_moduleview_ll_rightimg);
        ll_title= (LinearLayout) findViewById(R.id.view_moduleview_ll_title);
    }
    public ModuleView isShowTitle(boolean isShow){
        if(isShow) ll_title.setVisibility(View.VISIBLE);
        else ll_title.setVisibility(View.GONE);
        return this;
    }

    /**
     * 设置模块名
     * @param title 模块名
     * @param titleColor 字体颜色
     * @return ModuleView
     */
    public ModuleView setTitle(String title,int titleColor){
        tv_title.setText(title);
        if(titleColor!=0){
            tv_title.setTextColor(titleColor);
        }
        return this;
    }

    /**
     * 是否显示右侧拓展按钮
     * @param isShow 是否显示 默认不显示
     * @param listener 按钮的点击事件
     * @return ModuleView
     */
    public ModuleView showRightImg(boolean isShow,OnClickListener listener){
        if(isShow){
            ll_right.setVisibility(View.VISIBLE);
            if(listener!=null){
                ll_right.setOnClickListener(listener);
            }
        }
        return this;
    }

    /**
     *  是否显示右侧拓展按钮
     * @param isShow 是否显示 默认不显示
     * @param listener 按钮的点击事件
     * @param imgResource 自定义按钮的图标
     * @return ModuleView
     */
    public ModuleView showRightImg(boolean isShow,OnClickListener listener,int imgResource){
        showRightImg(isShow,listener);
        img_right.setImageResource(imgResource);
        return this;
    }

    /**
     * 更换左侧效果竖线的颜色。 默认是粉红色
     * @param color 颜色值
     * @return ModuleView
     */
    public ModuleView changeTitleImgColor(int color){
        img_title.setBackgroundColor(color);
        return this;
    }

    /**
     * 初始化模块的内容。使用的是recyclerView。通过ModuleViewBean中的LayoutNum参数控制显示线性布局或者网格布局
     *
     * @param context
     * @param list 内容数据 默认只会展示前面4条数据。其他数据在更多里展示。
     * @return ModuleView
     */
    public ModuleView showRecycelerView(Context context, List<ModuleViewBean> list,BaseRecyclerAdapter adapt){
        adapter=adapt;
        adapter.setList(list);
        if(list.size()==0)return this;
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        return this;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
