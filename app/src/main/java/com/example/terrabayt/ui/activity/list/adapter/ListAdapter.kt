package com.example.terrabayt.ui.activity.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemMainDefaultBinding
import com.example.terrabayt.databinding.ItemMainTopBinding
import com.example.terrabayt.ui.activity.common.PostModelMain
import com.example.terrabayt.ui.activity.list.adapter.holder.ListDefaultViewHolder
import com.example.terrabayt.ui.activity.list.adapter.holder.ListTopViewHolder
import com.example.terrabayt.ui.activity.list.presenter.ListPresenter
import com.example.terrabayt.ui.base.BaseAdapter

class ListAdapter(private val presenter: ListPresenter, private val categoryId: Int) :
    BaseAdapter<PostModelMain>(presenter) {
    private val default = 0
    private val top = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == default)
            ListDefaultViewHolder(ItemMainDefaultBinding.inflate(inflater, parent, false))
        else
            ListTopViewHolder(ItemMainTopBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = list[position]
        if (holder is ListDefaultViewHolder)
            holder.bind(model, presenter)
        if (holder is ListTopViewHolder)
            holder.bind(model, presenter)
    }

    override fun nextPage() {
        if (categoryId != 0)
            presenter.loadPosts(
                first_update = list[list.size - 1].updated_at,
                category = categoryId
            )
    }

    override fun getItemViewType(position: Int) = if (position % 10 != 0) default else top
}