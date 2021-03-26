package com.otrium.base.di.util

import android.content.Context
import com.otrium.base.di.component.BaseComponent

object InjectUtils {

    /**
     * Provide a single instance of the BaseComponent with the provided context
     *
     * @param applicationContext    Context
     * @return An instance of the BaseComponent
     */
    fun provideBaseComponent(applicationContext: Context): BaseComponent {

        return if (applicationContext is BaseComponentProvider) {
            (applicationContext as BaseComponentProvider).provideBaseComponent()
        } else {
            throw IllegalStateException("BaseComponentProvider cannot be provided.")
        }

    }

}