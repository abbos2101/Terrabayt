package com.example.terrabayt.module.net.model

data class PostModelNet(
    val id: Int,
    val title: String,
    val excerpt: String,
    val content: String,
    val published_at: Int,
    val updated_at: Int,
    val post_id: String,
    val post_modified: String,
    val category_id: Int,
    val category_name: String,
    val image: String,
    val url: String,
    val priority: String,
    val order: String
)