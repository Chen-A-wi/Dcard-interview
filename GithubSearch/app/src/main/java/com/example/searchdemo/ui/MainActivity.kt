package com.example.searchdemo.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.searchdemo.R
import com.example.searchdemo.common.utils.startFragment
import com.example.searchdemo.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment(R.id.mainActivity, SearchFragment())
    }
}