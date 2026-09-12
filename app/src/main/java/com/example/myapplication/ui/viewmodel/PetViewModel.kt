package com.example.myapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.Pet
import com.example.myapplication.data.repository.PetRepository

class PetViewModel : ViewModel() {
    private val petRepository = PetRepository()

    // 使用 Compose 的 observable 列表，这样新增数据UI会自动刷新
    var pets = mutableStateListOf<Pet>()
        private set

    init {
        loadPets() // 初始化加载
    }

    fun loadPets() {
        pets.clear()
        pets.addAll(petRepository.getAllPets())
    }

    fun uploadPet(name: String, desc: String) {
        if (name.isNotBlank()) {
            petRepository.addPet(Pet(id = System.currentTimeMillis().toString(), name = name, description = desc))
            loadPets() // 重新加载列表
        }
    }
}