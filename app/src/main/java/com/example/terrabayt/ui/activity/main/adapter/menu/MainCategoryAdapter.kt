package com.example.terrabayt.ui.activity.main.adapter.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terrabayt.databinding.ItemMainCategoryBinding
import com.example.terrabayt.ui.activity.main.presenter.MainPresenter
import com.example.terrabayt.ui.base.BaseAdapter
import com.example.terrabayt.ui.activity.main.adapter.menu.holder.MainCategoryViewHolder
import com.example.terrabayt.ui.activity.main.adapter.menu.model.CategoryModelMain

class MainCategoryAdapter(private val presenter: MainPresenter) :
    BaseAdapter<CategoryModelMain>(presenter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainCategoryViewHolder(ItemMainCategoryBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = list[position]
        (holder as MainCategoryViewHolder).bind(model, presenter)
    }
}