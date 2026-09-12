package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Pet

class PetRepository {
    // 模拟内存数据库，默认给一条数据
    private val petList = mutableListOf(
        Pet(id = "1", name = "小白", description = "一只可爱的中华田园猫，找领养。", imageUrl = "1")
    )

    fun getAllPets(): List<Pet> {
        return petList.toList() // 返回副本
    }

    fun addPet(pet: Pet) {
        petList.add(pet)
    }
}