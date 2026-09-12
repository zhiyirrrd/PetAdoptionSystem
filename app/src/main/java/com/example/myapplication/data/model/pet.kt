package com.example.myapplication.data.model

data class Pet(
    val id: String = "",
    val name: String = "未知宠物",
    val description: String = "",
    val imageUrl: String = "", // 暂时用纯色代替图片展示
    val uploaderId: String = ""
)