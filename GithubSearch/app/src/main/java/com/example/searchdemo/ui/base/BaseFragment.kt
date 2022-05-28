package com.example.searchdemo.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.afollestad.materialdialogs.MaterialDialog
import com.example.searchdemo.R
import com.example.searchdemo.data.ErrorMessage
import com.example.searchdemo.ui.MainActivity

abstract class BaseFragment : Fragment(){
    lateinit var act: MainActivity
    lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        act = activity as MainActivity
        ctx = requireContext()
    }

    fun observeErrorEvent(errorEvent: MutableLiveData<ErrorMessage>) {
        errorEvent.observe(viewLifecycleOwner) {
            it?.let {
                showErrorDialog(it)
            }
        }
    }

    private fun showErrorDialog(errorMessage: ErrorMessage) {
        MaterialDialog(ctx).show {
            title(res = R.string.alert_api_error)
            cancelable(false)
            message(text = errorMessage.message(ctx))
        }.positiveButton(res = R.string.btn_confirm)
    }
}