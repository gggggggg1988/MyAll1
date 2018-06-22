package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class ScrollListenWebview extends WebView {
    private ScrollListener m_scrollListener;

    public void setScrollListener(ScrollListener scrollListener) {
        m_scrollListener = scrollListener;
    }

    public interface ScrollListener{
        void onScrollChange(int l, int t, int lo, int to);
    }

    public ScrollListenWebview(Context context) {
        super(context);

    }

    public ScrollListenWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollListenWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        m_scrollListener.onScrollChange(l,t,oldl,oldt);
    }
}
