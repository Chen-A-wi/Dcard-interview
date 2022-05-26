package com.example.searchdemo.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.searchdemo.databinding.FragmentSearchBinding
import com.example.searchdemo.ui.base.BaseFragment
import com.example.searchdemo.ui.search.repositorieslist.RepositoriesListAdapter
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
        }
    }

    private fun observeLiveData() {
        vm.apply {
            notifyEvent.observe(viewLifecycleOwner) { dataList ->
                binding.rcvRepositoriesList.adapter?.notifyItemInserted(dataList.size)
            }
        }
    }
}