package com.example.terrabayt.ui.activity.main.adapter.menu.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemSimpleTvBinding
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryChildModelMain

class MainCategoryChildViewHolder(private val binding: ItemSimpleTvBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CategoryChildModelMain, presenter: MainPresenter) {
        binding.tv.text = model.name
        binding.llRoot.setOnClickListener {
            presenter.categoryChildItemClick(model)
        }
    }
}