package com.otrium.base.di.component

import android.app.Application
import com.otrium.base.di.module.BaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {

    fun inject(app: Application)

}