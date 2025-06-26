package com.applovin.sdk.view

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity

/**
 * Date：2025/6/26
 * Describe:
 */
class AppLovinViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback { }
    }
}