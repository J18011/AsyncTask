package com.example.asynctask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.Button

class MainActivity : AppCompatActivity(), OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        if(v!!.id==R.id.button) {
            Log.d("TAG","HELLO")
            MyAsyncTask(this).execute("Param1")
        }
        }
    }
