package com.example.terrabayt.ui.activity.main.adapter.main.holder

import abbos2101.mvvmdemo.common.setSrc
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemMainDefaultBinding
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.activity.common.PostModelMain

class MainDefaultViewHolder(private val binding: ItemMainDefaultBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: PostModelMain, presenter: MainPresenter) {
        binding.img.setSrc(model.image)
        binding.tvTitle.text = model.title
        binding.tvCategory.text = model.category_name
        binding.tvDate.text = model.post_modified

        binding.llRoot.setOnClickListener {
            presenter.postItemClick(model)
        }
    }
}