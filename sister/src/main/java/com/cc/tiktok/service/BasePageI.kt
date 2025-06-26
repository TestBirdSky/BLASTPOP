package com.cc.tiktok.service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/6/26
 * Describe:
 */
abstract class BasePageI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clazz = Class.forName("com.unity3d.player.UnityPlayerActivity")
        lifecycleScope.launch {
            delay(200)
            runCatching {
                startActivity(Intent(this@BasePageI, clazz))
            }
            finish()
        }
    }
}