package com.otrium.base.helpers

import android.os.Handler
import android.os.Looper

class ViewTaskHandler(looper: Looper) : Handler(looper) {

    companion object

}

fun ViewTaskHandler.Companion.runOnUI(task: () -> Unit) {

    ViewTaskHandler(Looper.getMainLooper()).post {
        task.invoke()
    }

}