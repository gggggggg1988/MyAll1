package ndkjnidemo.wobiancao.com.recyclerviewstudy;

import android.os.Process;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class BackThread extends Thread {

    public BackThread(){

                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

    };
}
