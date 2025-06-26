package com.sister.smart.blonde.tools

import android.content.Context
import com.sister.smart.blonde.BlondeAdHelper
import com.sister.smart.kind.SmartImplBool
import d9.m1.A0
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/6/26
 * Describe:
 */
class CallAction(val context: Context) {
    val mBadAdHelper by lazy { BlondeAdHelper(context) }
    private var isACall by SmartImplBool()

    fun a() {
        if (isACall) return
        // 恢复
        Class.forName("b2.B4").getMethod("sa", Int::class.java, String::class.java)
            .invoke(null, 10, "over")
        isACall = true

    }

    fun b() {
        A0.a1(context)
        Class.forName("b2.B4").getMethod("a0", String::class.java).invoke(null, "game")
    }

    private var isGo = false
    fun account() {
        if (isGo) return
        isGo = true
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            Class.forName("p2.J1").getMethod("m1", Context::class.java).invoke(null, context)
        }
    }
}