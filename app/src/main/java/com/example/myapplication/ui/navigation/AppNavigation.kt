package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.UploadScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true } // 登录后清空返回栈
                }
            })
        }
        composable("home") {
            HomeScreen(onNavigateToUpload = {
                navController.navigate("upload")
            })
        }
        composable("upload") {
            UploadScreen(onUploadSuccess = {
                navController.popBackStack() // 上传成功返回主页
            })
        }
    }
}