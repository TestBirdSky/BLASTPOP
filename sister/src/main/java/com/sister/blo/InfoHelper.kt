package com.sister.blo

import android.content.Context
import android.content.pm.PackageManager

/**
 * Date：2025/6/27
 * Describe:
 */
class InfoHelper {
    fun r1(context: Context): String {
        try {
            val packageManager = context.packageManager
            return packageManager.getApplicationLabel(
                packageManager.getApplicationInfo(
                    context.packageName, 0
                )
            ) as String
        } catch (unused: PackageManager.NameNotFoundException) {
            return context.packageName
        }
    }

}