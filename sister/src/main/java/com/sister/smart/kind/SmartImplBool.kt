package com.sister.smart.kind

import kotlin.reflect.KProperty

/**
 * Date：2025/6/25
 * Describe:
 */
class SmartImplBool(private val def: Boolean = false) {
    private val getSmartSp get() = HelperApp.mSharedPreference

    private fun getSmartName(name: String): String {
        return "Smart_${name.take(2)}${name.takeLast(2)}_$name"
    }

    operator fun getValue(me: Any?, p: KProperty<*>): Boolean {
        return getSmartSp.getBoolean(getSmartName(p.name), def)
    }

    operator fun setValue(me: Any?, p: KProperty<*>, value: Boolean) {
        getSmartSp.edit().putBoolean(getSmartName(p.name), value).apply()
    }
}