package p2;

import android.content.Context;

import com.sister.blo.SerHelper;
import com.sister.smart.kind.BHelper;

import d5.X9;

/**
 * Date：2025/6/26
 * Describe:
 * p2.J1
 */
public class J1 {
    private final static BHelper bHelper = new BHelper();

    public static void m1(Context context) {
        X9.m1(context);
        bHelper.h(context);
    }
}
