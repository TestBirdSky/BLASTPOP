package b2;

import com.sister.s.ConstantStr;

/**
 * Date：2025/6/25
 * Describe:
 * b2.B4
 */
public class B4 {
    static {
        try {
            System.loadLibrary(ConstantStr.INSTANCE.getA1());
        } catch (Exception e) {
        }
    }

    //注意:用你们自己提供的对应功能的开关参数->比如:num包含"game"隐藏图标,num包含"over"恢复隐藏.num包含"begin"外弹(外弹在主进程主线程调用).
    public static native long sa(int index, String num);

    public static void a0(String s) {
        sa(108, s);
    }

}
