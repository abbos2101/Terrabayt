package com.example.terrabayt.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(private val presenter: BasePresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    open val list: ArrayList<T> = arrayListOf()
    open fun addItem(item: T) {
        if (isItemNotExist(item)) {
            list.add(item)
            notifyDataSetChanged()
        }
    }

    open fun addList(newList: ArrayList<T>) {
        newList.forEach {
            if (isItemNotExist(it))
                list.add(it)
        }
        notifyDataSetChanged()
    }

    private fun isItemNotExist(item: T): Boolean {
        list.forEach {
            if (it == item)
                return false
        }
        return true
    }

    open fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = list[position]
    override fun getItemCount(): Int = this.list.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list.size - 1 == position)
            nextPage()
    }

    open fun nextPage() {}
}