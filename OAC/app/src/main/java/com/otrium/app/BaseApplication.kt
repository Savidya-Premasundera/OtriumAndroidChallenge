package com.otrium.app

import android.app.Application
import com.otrium.base.di.component.BaseComponent
import com.otrium.base.di.component.DaggerBaseComponent
import com.otrium.base.di.util.BaseComponentProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class BaseApplication : Application(), BaseComponentProvider, HasAndroidInjector {

    lateinit var baseComponent: BaseComponent

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {

        return this.dispatchingAndroidInjector

    }

    override fun onCreate() {

        super.onCreate()
        baseComponent = DaggerBaseComponent
            .builder()
            .build()
        baseComponent.inject(this)

    }

    override fun provideBaseComponent(): BaseComponent {

        return baseComponent

    }

}