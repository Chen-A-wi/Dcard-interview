package com.example.searchdemo.common.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.searchdemo.R

enum class TransactionType {
    PUSH, PRESENT, NONE;

    fun setCustomAnimations(ft: FragmentTransaction) {
        when (this) {
            PUSH -> ft.setCustomAnimations(
                R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit
            )
            PRESENT -> ft.setCustomAnimations(
                R.anim.fragment_slide_bottom_enter,
                R.anim.fragment_slide_bottom_exit,
                R.anim.fragment_slide_top_enter,
                R.anim.fragment_slide_top_exit
            )
            NONE -> {}
        }
    }
}

fun AppCompatActivity.startFragment(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment).commit()
}

fun Fragment.startFragment(
    @IdRes containerId: Int,
    fragment: Fragment,
    isAddToBackStack: Boolean = true
) {
    parentFragmentManager.beginTransaction().apply {
        add(containerId, fragment)
        if (isAddToBackStack) {
            addToBackStack(null)
        }
        commit()
    }
}

fun Fragment.startFragment(
    @IdRes containerId: Int,
    current: Fragment,
    target: Fragment,
    transactionType: TransactionType = TransactionType.PUSH
) {
    parentFragmentManager.beginTransaction().apply {
        transactionType.setCustomAnimations(this)
        if (!target.isAdded) {
            hide(current)
            add(containerId, target)
            addToBackStack(null)
            commit()
        } else {
            hide(current)
            show(target)
            addToBackStack(null)
            commit()
        }
    }
}

fun Fragment.hideKeyboard(v: View) {
    val imm =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}

fun Fragment.showKeyboard(v: View) {
    val imm =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
}