package fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myall.Multi2;
import com.example.administrator.myall.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicFragment extends BaseFragment implements View.OnClickListener, ControllerListener<ImageInfo> {
//ImageView imageView;
SimpleDraweeView draweeView;
    ImageLoader m_imageLoader = ImageLoader.getInstance();
    DisplayImageOptions option;
    public PicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Uri uri = Uri.parse("http://f.hiphotos.baidu.com/image/pic/item/dbb44aed2e738bd4f60e4017a48b87d6277ff9ed.jpg");
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        //draweeView.setImageURI(uri);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(this)
                .build();
        draweeView.setController(controller);
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources()).setFadeDuration(2000)
                                               .setBackground(mActivity.getDrawable(R.mipmap.mv1)).setFailureImage(mActivity.getDrawable(R.mipmap.mv2))
                .build();
        //draweeView.setController(controller);
        draweeView.setOnClickListener(this);

//        imageView = (ImageView) view.findViewById(R.id.my_image_view);
//        m_imageLoader.displayImage("http://f.hiphotos.baidu.com/image/pic/item/dbb44aed2e738bd4f60e4017a48b87d6277ff9ed.jpg",imageView,option);


    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        option  = new DisplayImageOptions.Builder().showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(true).cacheOnDisc(true).build();

        return inflater.inflate(R.layout.fragment_pic, container, false);
    }

    @Override
    public void onClick(View v) {
        mActivity.startActivity(new Intent(mActivity, Multi2.class));
    }

    @Override
    public void onSubmit(String id, Object callerContext) {

    }

    @Override
    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {

    }

    @Override
    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {

    }

    @Override
    public void onIntermediateImageFailed(String id, Throwable throwable) {

    }

    @Override
    public void onFailure(String id, Throwable throwable) {

    }

    @Override
    public void onRelease(String id) {

    }
}
