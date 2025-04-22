package com.unity3d.player.ui.info

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sister.smart.blonde.Headband


/**
 * Date：2025/4/22
 * Describe:
 */
class IPlayerActivity : AppCompatActivity() {
    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        runCatching {
            Headband.jumpPlayer(this@IPlayerActivity)
//            startActivity(playerInt(ComponentName(this, "com.unity3d.player.UnityPlayerActivity")))
            finish()
        }
    }

//    private fun playerInt(componentName: ComponentName): Intent {
//        val intent = Intent()
//        intent.setComponent(componentName)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        return intent
//    }
}