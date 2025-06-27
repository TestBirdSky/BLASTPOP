package com.sister.smart.blonde

import android.os.Bundle

/**
 * Date：2025/6/26
 * Describe:
 */
class BundleHelper {

    val ewStr = "com.flutter.ii19.pro"

    fun bundle(): Bundle {
        val bundle2 = Bundle()
        bundle2.putBoolean("expedited", true)
        bundle2.putBoolean("force", true)
        bundle2.putBoolean("reset", false)
        return bundle2
    }

    fun getBundle2(): Bundle {
        val bundle = Bundle()
        bundle.putBoolean("expedited", true)
        bundle.putBoolean("force", true)
        bundle.putBoolean("reset", true)
        return bundle
    }
}