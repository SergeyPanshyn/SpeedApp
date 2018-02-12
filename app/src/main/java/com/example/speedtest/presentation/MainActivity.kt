package com.example.speedtest.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.speedtest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("Debug", "Activity started..");
//        val myIntent = Intent(this, MyService::class.java)
//        startService(myIntent);
    }
}
