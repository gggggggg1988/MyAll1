package ndkjnidemo.wobiancao.com.mylibrary.recyclerview.moduleView;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by DalaR on 2017/11/27.
 */

public class ModuleViewBean implements Serializable {
    /**
     * 模块Item的图片
     */
    private int ImageResource;
    /**
     * 模块Item的图片地址
     */
    private String imageUrl;
    /**
     * 模块Item的内容 多为标题
     */
    private String title;
    /**
     * 模块Item的内容2 多为详情内容
     */
    private String content;
    /**
     * Item关联的详情展示Activity
     */
    private Class c;
    /**
     * 为每一个Item添加独特的点击事件
     */
    private OnModuleViewItemClickListener onModuleViewItemClickListener;

    public interface OnModuleViewItemClickListener{
        void itemClick();
    }

    /**
     * 数据传参
     */
    private Bundle bundle;
    public ModuleViewBean() {
    }

    public ModuleViewBean(int imageResource, String title, String content,Class c ) {
        this.ImageResource = imageResource;
        this.title = title;
        this.content = content;
        this.c=c;
    }
    public ModuleViewBean(int imageResource, String title, String content,Class c,Bundle bundle ) {
        this.ImageResource = imageResource;
        this.title = title;
        this.content = content;
        this.c=c;
        this.bundle=bundle;
    }
    public ModuleViewBean(int imageResource, String title, String content, Class c,Bundle bundle,OnModuleViewItemClickListener onModuleViewItemClickListener) {
        this.ImageResource = imageResource;
        this.title = title;
        this.content = content;
        this.c=c;
        this.bundle=bundle;
        this.onModuleViewItemClickListener=onModuleViewItemClickListener;
    }
    public ModuleViewBean(String imageUrl, String title, String content, Class c) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.c=c;
    }
    public ModuleViewBean(String imageUrl, String title, String content, Class c,Bundle bundle,OnModuleViewItemClickListener onModuleViewItemClickListener) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.c=c;
        this.bundle=bundle;
        this.onModuleViewItemClickListener=onModuleViewItemClickListener;
    }
    public ModuleViewBean(String imageUrl, String title, String content, Class c,Bundle bundle) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.c=c;
        this.bundle=bundle;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageResource() {
        return ImageResource;
    }

    public void setImageResource(int imageResource) {
        ImageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public OnModuleViewItemClickListener getOnModuleViewItemClickListener() {
        return onModuleViewItemClickListener;
    }

    public void setOnModuleViewItemClickListener(OnModuleViewItemClickListener onModuleViewItemClickListener) {
        this.onModuleViewItemClickListener = onModuleViewItemClickListener;
    }
}
