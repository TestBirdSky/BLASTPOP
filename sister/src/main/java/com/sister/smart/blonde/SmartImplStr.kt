package com.sister.smart.blonde

import kotlin.reflect.KProperty

/**
 * Date：2025/4/21
 * Describe:
 */
class SmartImplStr(private val def: String = "") {
    operator fun getValue(me: Any?, p: KProperty<*>): String {
        return GirlApp.mGirlMMKV.decodeString(p.name, def) ?: def
    }

    operator fun setValue(me: Any?, p: KProperty<*>, value: String) {
        GirlApp.mGirlMMKV.encode(p.name, value)
    }
}