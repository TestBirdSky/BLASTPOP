package com.sister.smart.blonde.tools

import com.sister.smart.blonde.Headband
import com.sister.smart.blonde.SmartImplStr
import com.sister.smart.blonde.c.SmartImplInt
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Date：2025/6/26
 * Describe:
 */
class LimitHelper {

    var showHourNumDay by SmartImplInt()
    var showDayNumDay by SmartImplStr()
    var clickDayNumDay by SmartImplInt()
    private var lastDayStr by SmartImplStr()
    private var lastHourStr by SmartImplStr("0")

    private var isPostLimit = false
    var showMaxHour = 60
    var showMaxDay = 60
    var clickMaxDay = 60
    fun isSmartLimit(post: () -> Unit): Boolean {
        if (isCurDay()) {
            val size1 = showHourNumDay
            val size2 = showDayNumDay.length
            val size3 = clickDayNumDay
//            Log.e("Boss-->", "isSmartLimit: $size1 --$size2 --$size3" )
            if (size2 >= showMaxDay || size3 >= clickMaxDay) {
                if (isPostLimit.not()) {
                    isPostLimit = true
                    post.invoke()
                }
                return true
            }
            if (size1 >= showMaxHour) {
                return true
            }
        }
        return false
    }


    private fun isCurDay(): Boolean {
        val day = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        if (day != lastDayStr) {
            lastDayStr = day
            isPostLimit = false
            showHourNumDay = 0
            showDayNumDay = ""
            clickDayNumDay = 0
            return false
        }
        val time = lastHourStr.toLong()
        if (System.currentTimeMillis() - time > 60000 * 60) {
            showHourNumDay = 0
            lastHourStr = System.currentTimeMillis().toString()
        }
        return true
    }

}