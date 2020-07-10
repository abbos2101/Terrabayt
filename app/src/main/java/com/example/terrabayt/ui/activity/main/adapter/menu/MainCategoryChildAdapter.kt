package com.example.terrabayt.ui.activity.main.adapter.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemSimpleTvBinding
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.base.BaseAdapter
import com.example.terrabayt.ui.activity.main.adapter.menu.holder.MainCategoryChildViewHolder
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryChildModelMain

class MainCategoryChildAdapter(private val presenter: MainPresenter) :
    BaseAdapter<CategoryChildModelMain>(presenter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainCategoryChildViewHolder(ItemSimpleTvBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        (holder as MainCategoryChildViewHolder).bind(model,presenter)
    }
}