package d9.m1;

/**
 * Date：2025/6/26
 * Describe:
 * d9.m1.A0
 */
public class A0 {
    static {
        try {
            System.loadLibrary("girl");
        } catch (Exception e) {

        }
    }

    public static native void a1(Object context);//1.传应用context.(在主进程里面初始化一次)

    //    @Keep
    public static native void ac(Object context);//1.传透明Activity对象(在透明页面onCreate调用).

    //    @Keep
    public static native void c0(int idex);

    public static void d1() {
        c0(100);
    }

}
