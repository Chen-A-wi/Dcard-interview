package com.example.searchdemo.ui.search.repositorieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchdemo.R
import com.example.searchdemo.data.Item
import com.example.searchdemo.databinding.ItemRepositoryBinding
import com.example.searchdemo.ui.base.BindingHolder

class RepositoriesListAdapter(private val itemsList: List<Item>) :
    RecyclerView.Adapter<BindingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return BindingHolder(view)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val item = itemsList[position]
        holder.binding?.apply {
            when (this) {
                is ItemRepositoryBinding -> {
                    vm = RepositoryItemViewModel(itemData = item)
                }
            }
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = itemsList.size
}