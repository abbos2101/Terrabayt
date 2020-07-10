package com.example.terrabayt.ui.activity.main.adapter.main.holder

import abbos2101.mvvmdemo.common.setSrc
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemMainTopBinding
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.activity.common.PostModelMain

class MainTopViewHolder(private val binding: ItemMainTopBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: PostModelMain, presenter: MainPresenter) {
        binding.img.setSrc(model.image)
        binding.tvTitle.text = model.title

        binding.llRoot.setOnClickListener {
            presenter.postItemClick(model)
        }
    }
}