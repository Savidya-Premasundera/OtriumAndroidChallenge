package com.otrium.base.helpers

import android.content.Context
import android.os.Handler
import com.otrium.base.dialog.ProgressDialog
import com.otrium.base.service.BaseRequest

object ProgressDialogHelper {

    private val elements: MutableList<Any> = mutableListOf()

    private fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    private fun push(item: Any) = elements.add(item)

    private fun pop(): Any? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            elements.removeAt(
                elements.size - 1
            )
        }
        return item
    }

    /**
     * Show dialog
     */
    fun showDialog(
        context: Context?,
        isForeground: Boolean,
        dialog: ProgressDialog
    ) {

        if (isForeground) {
            ViewTaskHandler.runOnUI {
                pop()
                push("SHOW")
                dialog.showDialog()
            }
        }

    }

    /**
     * Hide dialog
     */
    fun hideDialog(isForeground: Boolean, dialog: ProgressDialog) {

        if (isForeground) {
            ViewTaskHandler.runOnUI {
                pop()
                Handler().postDelayed({
                    if (isEmpty()) {
                        dialog.closeDialog()
                        BaseRequest.serviceListener?.onServiceStop(true)
                    }
                }, 500)
            }
        }

    }

}