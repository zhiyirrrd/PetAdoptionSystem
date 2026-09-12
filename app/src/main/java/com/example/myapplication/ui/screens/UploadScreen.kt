package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.viewmodel.PetViewModel

@Composable
fun UploadScreen(
    onUploadSuccess: () -> Unit,
    petViewModel: PetViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("上传宠物信息", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("宠物名字") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text("宠物描述") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                petViewModel.uploadPet(name, desc)
                onUploadSuccess() // 提交后返回主页
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("确认上传")
        }
    }
}