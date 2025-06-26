package s1;

import android.os.Handler;
import android.os.Message;

import d9.m1.A0;
import d9.m1.B1;


/**
 * Date：2025/6/26
 * Describe:
 * s1.p1
 */
public class p1 extends Handler {

    @Override
    public void handleMessage(Message message) {
        int r0 = message.what;
        B1 b1 = new B1();
        b1.a0(r0);
    }
}
