package com.example.projemanag.activities

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.projemanag.R
import com.example.projemanag.utils.Utils
import kotlinx.android.synthetic.main.activity_splash.tv_app_name

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val typeFace: Typeface = Typeface.createFromAsset(assets, "carbon bl.ttf")
        tv_app_name.typeface = typeFace
        Utils.countingIdlingResource.increment()
        Handler().postDelayed({
            // diasabled fot testing
//            val currentUserID = FirestoreClass().getCurrentUserId()
            val currentUserID = ""
            if (currentUserID.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
            Utils.countingIdlingResource.decrement()
        }, 2500)
    }
}
