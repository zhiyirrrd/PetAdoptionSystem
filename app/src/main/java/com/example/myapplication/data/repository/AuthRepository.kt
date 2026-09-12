package com.example.myapplication.data.repository

import com.example.myapplication.data.model.User

class AuthRepository {
    // 模拟登录：只要邮箱和密码不为空，就允许登录
    fun login(email: String, pass: String, onResult: (Boolean, User?) -> Unit) {
        if (email.isNotBlank() && pass.isNotBlank()) {
            onResult(true, User(uid = "123", email = email, username = email.substringBefore("@")))
        } else {
            onResult(false, null)
        }
    }
}