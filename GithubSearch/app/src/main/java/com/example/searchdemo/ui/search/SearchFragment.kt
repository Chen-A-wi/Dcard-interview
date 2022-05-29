package com.example.searchdemo.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.searchdemo.R
import com.example.searchdemo.common.ext.hideKeyboard
import com.example.searchdemo.databinding.FragmentSearchBinding
import com.example.searchdemo.ui.base.BaseFragment
import com.example.searchdemo.ui.search.repositorieslist.RepositoriesListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        initView()
        observeLiveData()
    }

    private fun initView() {
        vm.apply {
            binding.rcvRepositoriesList.adapter =
                RepositoriesListAdapter(itemsList = repositoriesList)
            lifecycleScope.launch {
                binding.etSearch.onTextChangedFlow()
                    .flowOn(scheduler.io())
                    .collectLatest { word ->
                        if (word.isNotBlank()) {
                            searchRepositories(
                                keyword = word.toString(),
                                page = 1,
                                restState = true
                            )
                        }
                    }
            }

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
}