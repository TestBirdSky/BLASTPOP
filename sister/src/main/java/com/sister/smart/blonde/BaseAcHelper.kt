package com.sister.smart.blonde

import android.app.Activity
import android.app.ActivityManager
import android.graphics.BitmapFactory
import android.os.Build

/**
 * Date：2025/6/26
 * Describe:
 */
abstract class BaseAcHelper {

    abstract fun inName(): Int
    abstract fun idIcon(): Int

    protected fun a(activity: Activity) {
        runCatching {
            modifyIcon(activity)
        }
    }

    private fun modifyIcon(activity: Activity) {
        with(activity) {
            // 在 Activity 的 onCreate() 方法中
            // 使用新的 Builder 模式 (API 28+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // 使用 Builder 创建 TaskDescription
                val taskDescription =
                    ActivityManager.TaskDescription.Builder().setLabel(getString(inName())) // 标题
                        .setIcon(idIcon()) // 图标（banner效果）
                        .setPrimaryColor(getColor(R.color.bs_s)) // 主色调
                        .build()
                setTaskDescription(taskDescription)
            } else {
                val bannerBitmap = BitmapFactory.decodeResource(resources, idIcon())
                val taskDescription = ActivityManager.TaskDescription(
                    getString(inName()),  // 标题
                    bannerBitmap,  // 图标（banner效果）
                    getColor(R.color.bs_s) // 主色调
                )
                setTaskDescription(taskDescription)
            }
        }
    }

}