package com.devapp.to_docompose.data.repositories

import com.devapp.to_docompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepoApp {
    fun getAllTasks(): Flow<List<ToDoTask>>
    fun sortByLowPriority(): Flow<List<ToDoTask>>
    fun sortByHighPriority(): Flow<List<ToDoTask>>
    fun getSelectedTask(taskId:Int):Flow<ToDoTask>
    fun searchDatabase(query:String):Flow<List<ToDoTask>>
    suspend fun deleteAllTasks()
    suspend fun deleteTask(task:ToDoTask)
    suspend fun insertTask(task:ToDoTask)
    suspend fun updateTask(task:ToDoTask)
}