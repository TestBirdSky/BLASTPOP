package com.unity3d.player

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/6/26
 * Describe:
 */
class UnityHelper {
    fun a(context: Context) {
        val re = Class.forName("c4.C1").getMethod("c0", Context::class.java).invoke(null, context)
        if (re?.toString()?.isNotBlank() == true) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(200)
                Class.forName("c2.B1").getMethod("b1", Context::class.java).invoke(null, context)
            }
        }
    }

}