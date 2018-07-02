package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myall.R;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class InfoLayout extends LinearLayout {
    public InfoLayout(Context context) {
        super(context);
        initViews(context);
    }

    private void initViews(Context context) {
        this.setOrientation(VERTICAL);
        this.addView(View.inflate(context, R.layout.info_layout, null));
    }

    public InfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        String text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
//        int photo = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "photo", R.mipmap.girl);
//        CircleView view = (CircleView) findViewById(R.id.circleView);
//        view.setImageResource(photo);
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(text);
    }
}
