package com.sister.smart.blonde.c

import com.sister.smart.blonde.BlondeNetPost
import com.sister.smart.blonde.Headband

/**
 * Date：2025/6/26
 * Describe:
 */
class HelperList(val mBlondeNetPost: BlondeNetPost) {

    fun check() :String{
        mBlondeNetPost.postEvent("isunlock")
        if (Headband.isSmartLimit {
                mBlondeNetPost.postEvent("getlimit")
            }) {
            mBlondeNetPost.postEvent("ispass", Pair("string", "limit"))
            return "1"
        }

        if (System.currentTimeMillis() - Headband.appInstallTime < Headband.timeWait) {
            mBlondeNetPost.postEvent("ispass", Pair("string", "www"))
            return "2"
        }
        if (System.currentTimeMillis() - Headband.showTimeAd < Headband.time2Per) {
            mBlondeNetPost.postEvent("ispass", Pair("string", "period"))
            return "3"
        }
        mBlondeNetPost.postEvent("ispass", Pair("string", "null"))
        return ""
    }
}