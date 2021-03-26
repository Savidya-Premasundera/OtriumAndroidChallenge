package com.otrium.base.di.util

import com.otrium.base.di.component.BaseComponent

interface BaseComponentProvider {

    /**
     * To provide only one instance of the BaseComponent
     *
     * @return BaseComponent
     */
    fun provideBaseComponent(): BaseComponent

}