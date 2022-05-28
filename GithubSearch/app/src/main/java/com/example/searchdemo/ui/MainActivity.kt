package com.example.searchdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchdemo.R
import com.example.searchdemo.common.utils.startFragment
import com.example.searchdemo.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(R.id.mainActivity, SearchFragment())
    }
}