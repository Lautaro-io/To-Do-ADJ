package com.chelo.todoadj.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.todoadj.model.entities.Task
import com.chelo.todoadj.model.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: TaskRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state

    init {
        getTasks()
    }

    fun getTasks() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            combine(
                repo.getTasksCompleted(),
                repo.getTasksPending()
            ) { completedTasks, pendingTasks ->
                Pair(
                    completedTasks,
                    pendingTasks
                )
            }.collect { (completed, pending) ->
                _state.value = _state.value.copy(
                    completedTasks = completed,
                    pendingTasks = pending
                )
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repo.updateTask(task)
            _state.value = _state.value.copy(selectedTask = null)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repo.deleteTask(task)
        }
    }

    fun changeDialogState() {
        _state.value = _state.value.copy(showDialog = !_state.value.showDialog)
    }

    fun changeDialogDelete(){
        _state.value = _state.value.copy(showDialogDelete = !_state.value.showDialogDelete)
    }

    fun changeTab(tab: String) {
        _state.value = _state.value.copy(selectedTab = tab)
    }

    fun selectTask(task: Task){
        _state.value = _state.value.copy(selectedTask = task)
    }
    fun openCreateDialog() {
        _state.value = _state.value.copy(
            showDialog = true,
            selectedTask = null
        )
    }

}


data class HomeState(
    val completedTasks: List<Task> = emptyList(),
    val pendingTasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val showDialogDelete : Boolean = false,
    val selectedTab: String = "Pendientes",
    val selectedTask: Task? = null
)
