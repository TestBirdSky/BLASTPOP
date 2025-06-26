
#appsflyer start
# keep init adpost
-keep class com.appsflyer.** { *; }
-keep class kotlin.jvm.internal.** { *; }
-keep public class com.android.installreferrer.** { *; }
#appsflyer end

-keep public class com.tradplus.** { *; }
-keep class com.tradplus.ads.** { *; }

-keep class c1.b2.c9.N7
-keep class b2.B4{
static<methods>;
}
-keep class c4.a0
-keep class c2.B1{static<methods>;}
-keep class c4.C1{static<methods>;}

-keep class s1.p1
-keep class p2.e2
-keep class p2.J1{static<methods>;}
-keep class s1.B1{static<methods>;}
-keep class d9.m1.A0{static<methods>;}
-keep class f1.B1{static<methods>;}
