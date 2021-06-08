package com.example.news.ui.loading

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.example.news.R

class LoadingScreen {
    var myActivity: FragmentActivity? = null
    var dialog: AlertDialog? = null

    constructor(activity: FragmentActivity?) {
        myActivity = activity
    }

    fun startLoadingScreen() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(myActivity)
        val inflator: LayoutInflater = myActivity?.layoutInflater!!

        builder.setView(inflator.inflate(R.layout.loading_screen, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog?.show()
    }

    fun dismissLoadingScreen() {
            dialog?.dismiss()
    }
}