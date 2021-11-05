package com.devapp.to_docompose.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.data.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val app: Application,
    private val repository: ToDoRepository
) : AndroidViewModel(app) {
    private val _allTask = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTask:StateFlow<List<ToDoTask>> = _allTask
    fun getAllTask(){
        viewModelScope.launch {
            repository.getAllTasks().collect {
                _allTask.value = it
            }
        }
    }
}