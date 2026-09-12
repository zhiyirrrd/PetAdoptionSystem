package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.viewmodel.PetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToUpload: () -> Unit,
    petViewModel: PetViewModel = viewModel()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("所有宠物") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToUpload) {
                Text("+ 上传")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(petViewModel.pets) { pet ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // 模拟图片区域
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("图片展示区", color = Color.White)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("名字: ${pet.name}", style = MaterialTheme.typography.titleLarge)
                        Text("描述: ${pet.description}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}