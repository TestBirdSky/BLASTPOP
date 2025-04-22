package com.unity3d.player.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity

/**
 * Date：2025/4/22
 * Describe:com.unity3d.player.ui.PlayerUnityPage
 */
class PlayerUnityPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback { }
    }
}