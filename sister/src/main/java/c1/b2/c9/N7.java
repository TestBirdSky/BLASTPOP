package c1.b2.c9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Date：2025/6/25
 * Describe:
 */
public class N7 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Intent eIntent = intent.getParcelableExtra("si1");
            if (eIntent != null) {
                context.startActivity(eIntent);
            }
        } catch (Exception e) {

        }
    }
}
