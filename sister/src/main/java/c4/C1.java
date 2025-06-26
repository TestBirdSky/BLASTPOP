package c4;

import android.content.Context;

import com.sister.smart.blonde.BlondeAdHelper;
import com.sister.smart.blonde.GirlCenter;
import com.sister.smart.blonde.tools.LogHelper;
import com.sister.smart.blonde.tools.Tools;

/**
 * Date：2025/6/26
 * Describe:
 * c4.C1
 */
public class C1 {

    public static String c0(Context context) {
        GirlCenter gc = new GirlCenter();
        String s = gc.check(context);
        LogHelper.INSTANCE.log(" c0--->$s");
        if (!s.isEmpty()) {
            i1(context);
        }
        return s;
    }

    private static void i1(Context context) {
        BlondeAdHelper blondeAdHelper = new BlondeAdHelper(context);
        blondeAdHelper.initBlonde();
    }

}
