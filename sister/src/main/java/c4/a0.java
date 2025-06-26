package c4;

import com.sister.smart.blonde.tools.Tools;

import java.io.File;
import java.io.IOException;

/**
 * Date：2025/6/26
 * Describe:
 * c4.a0
 */
public class a0 {

    public static void a1(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {

            }
        }
        a0.b2();
    }

    public static void b2() {
        Tools.INSTANCE.getMKindGooSister().kindEvent();
    }
}
