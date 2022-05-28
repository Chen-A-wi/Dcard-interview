package com.example.searchdemo.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.searchdemo.ui.MainActivity

abstract class BaseFragment : Fragment(){
    lateinit var act: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        act = activity as MainActivity
    }
}