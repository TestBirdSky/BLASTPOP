package com.sister.smart.blonde

import com.sister.smart.kind.HelperApp
import kotlin.reflect.KProperty

/**
 * Date：2025/4/21
 * Describe:
 */
class SmartImplStr(private val def: String = "") {
    private val mmkv get() = HelperApp.mMMKV

    private fun nameSmart(name: String): String {
        return "kind_$name"
    }

    operator fun getValue(me: Any?, p: KProperty<*>): String {
        return mmkv.decodeString(nameSmart(p.name), def) ?: def
    }

    operator fun setValue(me: Any?, p: KProperty<*>, value: String) {
        mmkv.encode(nameSmart(p.name), value)
    }
}