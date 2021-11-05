package com.devapp.to_docompose.data.repositories

import com.devapp.to_docompose.data.daos.TodoDao
import com.devapp.to_docompose.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val todoDao: TodoDao):ToDoRepoApp {
    override fun getAllTasks(): Flow<List<ToDoTask>> {
        return todoDao.getAllTasks()
    }

    override fun sortByLowPriority(): Flow<List<ToDoTask>> {
        return todoDao.sortByLowPriority()
    }

    override fun sortByHighPriority(): Flow<List<ToDoTask>> {
        return todoDao.sortByHighPriority()
    }

    override fun getSelectedTask(taskId:Int): Flow<ToDoTask> {
        return todoDao.getSelectedTask(taskId)
    }

    override fun searchDatabase(query: String): Flow<List<ToDoTask>> {
        return todoDao.searchDatabase(query)
    }

    override suspend fun deleteAllTasks() {
        return todoDao.deleteAllTask()
    }

    override suspend fun deleteTask(task: ToDoTask) {
        return todoDao.deleteTask(task)
    }

    override suspend fun insertTask(task: ToDoTask) {
        return todoDao.insetTask(task)
    }

    override suspend fun updateTask(task: ToDoTask) {
        return todoDao.updateTask(task)
    }

}