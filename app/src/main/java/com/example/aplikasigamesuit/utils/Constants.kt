package com.example.androidchallenge5.utils


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import com.example.aplikasigamesuit.R

object Constants {
    const val SPLASH_TIME_LONG: Long = 1500

    const val KEY_NAME = "key_name"
    const val KEY_PLAYER_TWO = "key_player_two"
    const val PLAYER_TWO = "Pemain 2"
    const val PLAYER_CPU = "CPU"

    fun showDialogInfo(
        context: Context
    ): Dialog {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.window!!.apply {
            val params: WindowManager.LayoutParams = this.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            attributes.windowAnimations = R.transition.fade
            setGravity(Gravity.CENTER)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setContentView(R.layout.custom_dialog_info)
        return dialog
    }
}