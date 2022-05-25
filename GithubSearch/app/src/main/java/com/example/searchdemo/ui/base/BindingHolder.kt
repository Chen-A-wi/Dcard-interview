package com.example.searchdemo.ui.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

class BindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleOwner {

    val binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    private val lifecycleRegistry = LifecycleRegistry(this)
    private var wasPaused: Boolean = false

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        binding?.lifecycleOwner = this
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun markCreated() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun markAttach() {
        if (wasPaused) {
            lifecycleRegistry.currentState = Lifecycle.State.RESUMED
            wasPaused = false
        } else {
            lifecycleRegistry.currentState = Lifecycle.State.STARTED
        }
    }

    fun markDetach() {
        wasPaused = true
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    fun markDestroyed() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}
