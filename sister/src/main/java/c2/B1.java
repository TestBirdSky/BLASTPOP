package c2;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
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

    private static String stringFbId = "";

    public static void c1(Application app, String fbId) {
        if (fbId.isEmpty()) return;
        if (stringFbId.equals(fbId)) return;
        stringFbId = fbId;
        FacebookSdk.setApplicationId(fbId);
        FacebookSdk.sdkInitialize(app);
        AppEventsLogger.activateApp(app);
    }

}
