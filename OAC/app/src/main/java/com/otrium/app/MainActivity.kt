package com.otrium.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.otrium.app.di.component.DaggerAppComponent
import com.otrium.base.di.util.InjectUtils
import com.otrium.base.helpers.NavigationHelper
import com.otrium.user.feature.activity.user.UserActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper()).postDelayed({
            NavigationHelper.openActivity(
                activity = this,
                toActivity = UserActivity::class.java,
                isCloseLastActivity = true
            )
        }, 3000)

        DaggerAppComponent
            .builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)

    }

}