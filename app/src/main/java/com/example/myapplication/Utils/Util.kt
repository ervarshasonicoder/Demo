package com.example.myapplication.Utils

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.myapplication.R

object  Util {
    private var mDialog: Dialog? = null
    fun showProgressDialog(context: Context) {
        try {
            if (mDialog != null && mDialog!!.isShowing) {
                return
            }
            mDialog = Dialog(context)
            mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog!!.setCancelable(false)
            mDialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            mDialog!!.setContentView(R.layout.custom_only_progress_dialog)
            mDialog!!.window!!.setLayout(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            mDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun dismissDialog() {
        try {
            if (mDialog != null && mDialog!!.isShowing) {
                mDialog!!.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}