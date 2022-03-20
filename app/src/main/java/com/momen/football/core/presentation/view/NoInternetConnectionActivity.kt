package com.momen.football.core.presentation.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.momen.football.R

class NoInternetConnectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet_connection)
        val filter2 = IntentFilter()
        filter2.addAction("com.momen.football.noNetwork")
        registerReceiver(receiver, filter2)
    }
    var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}