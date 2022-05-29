package com.example.searchdemo.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.example.searchdemo.R
import com.example.searchdemo.common.ext.hideKeyboard
import com.example.searchdemo.databinding.FragmentSearchBinding
import com.example.searchdemo.ui.base.BaseFragment
import com.example.searchdemo.ui.search.repositorieslist.RepositoriesListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.system.exitProcess

class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    private val vm by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            vm = this@SearchFragment.vm
            lifecycleOwner = this@SearchFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!vm.checkInternet(act)) {
            showPleaseInternetDialog()
        }
        initView()
        observeLiveData()
    }

    private fun initView() {
        vm.apply {
            textWatcher = onEditWatcher()

            binding.rcvRepositoriesList.adapter =
                RepositoriesListAdapter(itemsList = repositoriesList)

            binding.rcvRepositoriesList.addOnScrollListener(onScrollListener())
        }
    }

    private fun observeLiveData() {
        vm.apply {
            notifyEvent.observe(viewLifecycleOwner) {
                binding.rcvRepositoriesList.adapter?.notifyDataSetChanged()
            }

            clickLiveEvent.observe(viewLifecycleOwner) { id ->
                when (id) {
                    R.id.imgBtnCancel -> {
                        edtSearch.value = ""
                    }
                }
            }

            isFocus.observe(viewLifecycleOwner) { state ->
                if (!state) {
                    binding.etSearch.clearFocus()
                    hideKeyboard(binding.root)
                }
            }

            observeErrorEvent(errorEvent)
        }
    }

    private fun showPleaseInternetDialog() {
        MaterialDialog(ctx).show {
            cancelable(true)
            message(text = context.getString(R.string.alert_check_internet_msg))
        }.positiveButton(res = R.string.btn_confirm) {
            if (!vm.checkInternet(act)) {
                closeApp()
            }
        }.negativeButton(res = R.string.btn_cancel) {
            closeApp()
        }
    }

    private fun closeApp() {
        act.finish()
        exitProcess(0)
    }
}