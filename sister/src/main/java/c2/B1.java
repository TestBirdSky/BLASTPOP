package c2;

import android.content.Context;

import com.sister.smart.blonde.tools.Tools;
import com.sister.worker.WorkerHelper;

/**
 * Date：2025/6/26
 * Describe:
 * c2.B1
 */
public class B1 {

    public static void b1(Context context) {
        WorkerHelper wh = new WorkerHelper();
        wh.workStart(context);
        wh.workPeriod(context);
        Tools.INSTANCE.open(context);
    }

}
