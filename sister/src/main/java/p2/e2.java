package p2;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.sister.smart.blonde.tools.LogHelper;

import d9.m1.A0;

/**
 * Date：2025/6/26
 * Describe:
 * p2.e2
 */
public class e2 extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        // todo del
        LogHelper.INSTANCE.log("onProgressChanged--->"+newProgress);
        if (newProgress>97){
            A0.d1();
        }
    }
}
