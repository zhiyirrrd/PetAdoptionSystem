package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.AuthRepository

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    fun login(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        authRepository.login(email, pass) { success, _ ->
            if (success) onSuccess() else onError("请输入邮箱和密码")
        }
    }
}