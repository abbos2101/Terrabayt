package com.example.terrabayt.ui.activity.main.adapter.menu.holder

import abbos2101.mvvmdemo.common.isVisible
import abbos2101.mvvmdemo.common.setSrc
import abbos2101.mvvmdemo.common.setVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.R
import com.example.terrabayt.databinding.ItemMainCategoryBinding
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.activity.main.adapter.menu.MainCategoryChildAdapter
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryModelMain

class MainCategoryViewHolder(private val binding: ItemMainCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: CategoryModelMain, presenter: MainPresenter) {
        binding.tvCategory.text = model.name
        binding.rvChild.setVisible(false)
        binding.imgUpDown.setVisible(false)

        val hasChildItem = !model.child.isEmpty()
        binding.imgUpDown.setVisible(hasChildItem)
        if (hasChildItem) {
            binding.imgUpDown.setVisible(true)
            binding.imgUpDown.setSrc(R.drawable.ic_down)
            val adapter = MainCategoryChildAdapter(presenter)
            binding.rvChild.adapter = adapter
            adapter.addList(model.child)
        }
        llRootClick(hasChildItem, model, presenter)
    }

    private fun llRootClick(
        hasChildItem: Boolean,
        model: CategoryModelMain,
        presenter: MainPresenter
    ) {
        if (hasChildItem) {
            llRootClickChildView()
        } else {
            binding.llRoot.setOnClickListener {
                presenter.categoryItemClick(model)
            }
        }
    }

    private fun llRootClickChildView() {
        binding.llRoot.setOnClickListener {
            if (binding.rvChild.isVisible()) {
                binding.imgUpDown.setSrc(R.drawable.ic_down)
            } else
                binding.imgUpDown.setSrc(R.drawable.ic_up)
            binding.rvChild.setVisible(!binding.rvChild.isVisible())
        }
    }
}