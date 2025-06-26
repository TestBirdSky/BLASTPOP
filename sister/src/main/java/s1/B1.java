package s1;

import android.content.Context;
import android.os.IBinder;

import com.sister.helper.AbAccountHelper;

/**
 * Date：2025/6/26
 * Describe:
 * s1.B1
 */
public class B1 {
    private static AbAccountHelper mAbAccountHelper;

    public static IBinder b1(Context context) {
        if (mAbAccountHelper == null) {
            mAbAccountHelper = new AbAccountHelper(context);
        }
        return mAbAccountHelper.getIBinder();
    }

}
