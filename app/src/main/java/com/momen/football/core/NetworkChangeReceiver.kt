package com.momen.football.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.ConnectivityManager
import com.momen.football.core.presentation.view.NoInternetConnectionActivity


class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val intent1 = Intent(context, NoInternetConnectionActivity::class.java)
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            val local = Intent()
            local.action = "com.momen.football.noNetwork"
            context.sendBroadcast(local)

            // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                // connected to mobile data
            }
        } else {
            intent1.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent1)
            // not connected to the internet
        }
    }
}