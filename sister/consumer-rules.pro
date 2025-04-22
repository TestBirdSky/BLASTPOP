
#appsflyer start
# keep init adpost
-keep class com.appsflyer.** { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep public class com.android.installreferrer.** { *; }
#appsflyer end

-keep public class com.tradplus.** { *; }
-keep class com.tradplus.ads.** { *; }

-keep class a.b.CRe
-keep class com.sister.smart.blonde.AngelHelper{
    native<methods>;
    smart(java.lang.String);
}