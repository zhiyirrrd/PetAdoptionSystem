// ui/screens/LoginScreen.kt 修改后：
package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    // 加上 Surface 和 fillMaxSize，强制撑满屏幕并给一个白色背景
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // 强制白色背景
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("宠物领养系统", style = MaterialTheme.typography.headlineMedium, color = Color.Black)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("邮箱") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("密码") },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMsg.isNotBlank()) {
                Text(errorMsg, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    authViewModel.login(email, password,
                        onSuccess = { onLoginSuccess() },
                        onError = { errorMsg = it }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("登录 / 注册")
            }
        }
    }
}