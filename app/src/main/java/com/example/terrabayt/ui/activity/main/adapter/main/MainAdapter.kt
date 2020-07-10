package com.example.terrabayt.ui.activity.main.adapter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemMainDefaultBinding
import com.example.terrabayt.databinding.ItemMainTopBinding
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.main.adapter.main.holder.MainDefaultViewHolder
import com.example.terrabayt.ui.activity.main.adapter.main.holder.MainTopViewHolder
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.base.BaseAdapter

class MainAdapter(private val presenter: MainPresenter) :
    BaseAdapter<PostModelMain>(presenter) {
    private val default = 0
    private val top = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == default)
            MainDefaultViewHolder(ItemMainDefaultBinding.inflate(inflater, parent, false))
        else
            MainTopViewHolder(ItemMainTopBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = list[position]
        if (holder is MainDefaultViewHolder)
            holder.bind(model, presenter)
        if (holder is MainTopViewHolder)
            holder.bind(model, presenter)
    }

    override fun nextPage() {
        presenter.loadPosts(first_update = list[list.size - 1].updated_at)
    }

    override fun getItemViewType(position: Int) = if (position % 10 != 0) default else top
}