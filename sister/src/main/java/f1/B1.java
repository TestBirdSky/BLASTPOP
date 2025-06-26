package f1;

import android.content.Context;
import android.os.IBinder;

import com.sister.s.MyAbstractThreadedSyncAdapter;

/**
 * Date：2025/6/26
 * Describe:
 * f1.B1
 */
public class B1 {

    private static MyAbstractThreadedSyncAdapter myAbstractThreadedSyncAdapter;

    public static IBinder s1(Context context) {
        if (myAbstractThreadedSyncAdapter == null) {
            myAbstractThreadedSyncAdapter = new MyAbstractThreadedSyncAdapter(context, true);
        }
        return myAbstractThreadedSyncAdapter.getSyncAdapterBinder();
    }

}
