package com.devapp.to_docompose.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.data.repositories.ToDoRepository
import com.devapp.to_docompose.util.RequestState
import com.devapp.to_docompose.util.SearchAppBarState
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
    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTask: StateFlow<RequestState<List<ToDoTask>>> = _allTask
    fun getAllTask() {
        _allTask.value = RequestState.Loading
        viewModelScope.launch {
            try {
                repository.getAllTasks().collect {
                    _allTask.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                _allTask.value = RequestState.Error(e)
            }
        }
    }

    private val _selectedTask:MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask:StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId:Int){
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect {
                _selectedTask.value = it
            }
        }
    }
}