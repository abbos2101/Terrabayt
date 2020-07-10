package com.example.terrabayt.ui.activity.main.adapter.menu.model

data class CategoryModelMain(
    val id: Int = 0,
    val name: String = "",
    val slug: String = "",
    val child: ArrayList<CategoryChildModelMain> = arrayListOf()
)