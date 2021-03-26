package com.otrium.app.di.component

import com.otrium.app.MainActivity
import com.otrium.app.di.module.AppModule
import com.otrium.app.di.scope.AppScope
import com.otrium.base.di.component.BaseComponent
import dagger.Component

@AppScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

}