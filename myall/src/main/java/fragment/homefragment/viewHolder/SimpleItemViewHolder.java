package fragment.homefragment.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.myall.R;

import ndkjnidemo.wobiancao.com.mylibrary.recyclerview.base.ViewHolder;

public class SimpleItemViewHolder extends ViewHolder{
    private TextView m_textView;

    public SimpleItemViewHolder(Context context, View itemView) {
        super(context, itemView);
        m_textView=itemView.findViewById(R.id.text);
    }
}
