package com.otrium.base.helpers

import android.content.Context
import com.otrium.base.R
import com.otrium.base.util.ScreenUtil

/**
 * Set font size to device width
 *
 * @param fontWeight    Required size
 * @param context       Context
 * @return Font size according to the screen width
 */
fun setFontSizeToWidth(fontWeight: Float, context: Context): Float {

    val isTab = context.resources.getBoolean(R.bool.isTab)

    return if (!isTab) {
        val screenWidth = ScreenUtil.width.toFloat()
        val weight: Float = ((screenWidth / 100) * fontWeight)
        val density = context.resources.displayMetrics.density - 0.5f
        weight / density
    } else {
        val screenWidth = ScreenUtil.width.toFloat() / 1.6f
        val weight: Float = ((screenWidth / 100) * fontWeight)
        val density = context.resources.displayMetrics.density - 0.5f
        weight / density
    }

}

/**
 * Set font size to device height
 *
 * @param fontWeight    Required size
 * @param context       Context
 * @return Font size according to the screen height
 */
fun setFontSizeToHeight(fontWeight: Float, context: Context): Float {

    val isTab = context.resources.getBoolean(R.bool.isTab)

    return if (!isTab) {
        val screenHeight = ScreenUtil.height.toFloat()
        val weight: Float = ((screenHeight / 100) * fontWeight)
        val density = context.resources.displayMetrics.density - 0.5f
        weight / density
    } else {
        val screenHeight = ScreenUtil.height.toFloat() / 1.6f
        val weight: Float = ((screenHeight / 100) * fontWeight)
        val density = context.resources.displayMetrics.density - 0.5f
        weight / density
    }

}
