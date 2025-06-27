package d5;

import android.content.Context;

import com.sister.resolver.ResoImpl;

/**
 * Date：2025/6/27
 * Describe:
 * d5.X9
 */
public class X9 {
    private static final ResoImpl reso = new ResoImpl();

    public static void m1(Context context) {
        reso.setAccount(context);
    }
}
