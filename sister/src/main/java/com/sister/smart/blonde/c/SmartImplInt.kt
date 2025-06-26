package com.sister.smart.blonde.c

import com.sister.smart.kind.HelperApp
import kotlin.reflect.KProperty

/**
 * Date：2025/4/21
 * Describe:
 */
class SmartImplInt(private val def: Int = 0) {
    private val mmkv get() = HelperApp.mSharedPreference

    private fun nameSmart(name: String): String {
        return "num_i1_$name"
    }

    operator fun getValue(me: Any?, p: KProperty<*>): Int {
        return mmkv.getInt(nameSmart(p.name), def)
    }

    operator fun setValue(me: Any?, p: KProperty<*>, value: Int) {
        mmkv.edit().putInt(nameSmart(p.name), value).apply()
    }
}