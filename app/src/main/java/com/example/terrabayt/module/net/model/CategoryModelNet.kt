package com.example.terrabayt.module.net.model

data class CategoryModelNet(
    val id: Int,
    val name: String,
    val slug: String,
    val child: List<ChildModelNet>
)