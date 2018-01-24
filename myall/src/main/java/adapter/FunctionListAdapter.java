package adapter;

import android.content.Context;

import com.jcodecraeer.xrecyclerview.SwipeMenuMultiDelegateAdapter;

import java.util.List;

import adapter.delegates.FuncListDelegate;

/**
 * Created by Administrator on 2017/6/25 0025.
 */

public class FunctionListAdapter extends SwipeMenuMultiDelegateAdapter<String> {
    private Context m_context;

    public FunctionListAdapter(Context context,  List<String> datas) {
        super(datas);
        m_context = context;
        addItemViewDelegate(new FuncListDelegate(m_context));
    }


}
