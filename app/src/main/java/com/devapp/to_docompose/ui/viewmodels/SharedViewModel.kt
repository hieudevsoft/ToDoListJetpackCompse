package com.devapp.to_docompose.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devapp.to_docompose.data.models.Priority
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.data.repositories.DataStoreRepository
import com.devapp.to_docompose.data.repositories.ToDoRepository
import com.devapp.to_docompose.util.Action
import com.devapp.to_docompose.util.Constants
import com.devapp.to_docompose.util.RequestState
import com.devapp.to_docompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val app: Application,
    private val repository: ToDoRepository,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(app) {

    val action:MutableState<Action> = mutableStateOf(Action.NO_ACTION)

    val id: MutableState<Int> = mutableStateOf(-1)
    val title: MutableState<String> = mutableStateOf("")
    val content: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

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
        searchAppBarState.value = SearchAppBarState.CLOSED
    }


    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect {
                _selectedTask.value = it
            }
        }
    }

    fun updateTaskFields(task: ToDoTask?) {
        if (task != null) {
            id.value = task.id
            title.value = task.title
            content.value = task.content
            priority.value = task.priority
        } else {
            id.value = 0
            title.value = ""
            content.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle: String) {
        if (newTitle.length <= Constants.MAX_LENGTH_TITLE){
            title.value = newTitle
        }
    }

    fun validateFields():Boolean{
        return title.value.isNotEmpty() && content.value.isNotEmpty()
    }

    private fun insertTask(){
        viewModelScope.launch(Dispatchers.IO){
            val toDoTask = ToDoTask(
                title = title.value,
                content = content.value,
                priority = priority.value
            )
            repository.insertTask(toDoTask)
        }
    }

    private fun updateTask(){
        viewModelScope.launch(Dispatchers.IO){
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                content = content.value,
                priority = priority.value
            )
            repository.updateTask(toDoTask)
        }
    }

    private fun deleteTask(){
        viewModelScope.launch(Dispatchers.IO){
            val toDoTask = ToDoTask(
                id = id.value,
                title = title.value,
                content = content.value,
                priority = priority.value
            )
            repository.deleteTask(toDoTask)
        }
    }

    private fun deleteAllTask(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllTasks()
        }
    }

    fun handleDatabaseActions(action:Action){
        when(action){
            Action.INSERT->{
                insertTask()
            }
            Action.UPDATE->{
                updateTask()
            }
            Action.DELETE->{
                deleteTask()
            }
            Action.DELETE_ALL->{
                deleteAllTask()
            }
            Action.UNDO->{
                insertTask()
            }
            else->{

            }
        }
        this.action.value = Action.NO_ACTION
    }

    private val _searchedTask = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val searchedTask: StateFlow<RequestState<List<ToDoTask>>> = _searchedTask
    fun searchDatabase(searchQuery:String) {
        _searchedTask.value = RequestState.Loading
        viewModelScope.launch {
            try {
                repository.searchDatabase(searchQuery).collect {
                    _searchedTask.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                _searchedTask.value = RequestState.Error(e)
            }
        }
        searchAppBarState.value = SearchAppBarState.TRIGGERED
    }

    private val _sortState = MutableStateFlow<RequestState<Priority>>(RequestState.Idle)
    val sortState = _sortState

    fun persistSortingState(priority: Priority){
        viewModelScope.launch(Dispatchers.IO){
            dataStoreRepository.persisSortState(priority)
        }
    }

    fun readSortState(){
        _sortState.value = RequestState.Loading
        try {
            viewModelScope.launch {
                dataStoreRepository.readSortState()
                    .map { Priority.valueOf(it) }
                    .collect {
                        _sortState.value = RequestState.Success(it)
                }
            }
        }catch (e:Exception){
            _sortState.value = RequestState.Error(e)
        }
    }

    val lowPriorityTasks:StateFlow<List<ToDoTask>> =
        repository.sortByLowPriority().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        emptyList()
    )

    val highPriorityTasks:StateFlow<List<ToDoTask>> =
        repository.sortByHighPriority().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            emptyList()
        )

}