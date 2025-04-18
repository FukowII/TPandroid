package com.example.tpandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TaskApp()
            }
        }
    }
}

@Composable
fun TaskApp() {
    val navController = rememberNavController()
    val taskList = remember { mutableStateListOf<String>() }

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                navController = navController,
                taskList = taskList,
                onAddClick = { navController.navigate("add") }
            )
        }
        composable("add") {
            AddTaskScreen(
                onSave = { taskList.add(it) },
                onBack = { navController.popBackStack() }
            )
        }
        composable("detail/{taskName}") { backStackEntry ->
            val taskName = backStackEntry.arguments?.getString("taskName") ?: ""
            DetailScreen(taskName)
        }
    }
}