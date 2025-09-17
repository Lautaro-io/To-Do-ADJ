package com.chelo.todoadj.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.todoadj.model.entities.Task
import com.chelo.todoadj.model.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repo: TaskRepository) : ViewModel() {

    private val _state = MutableStateFlow(TaskState())
    val state = _state

    fun addTask(){
        val task = _state.value
        if (task.title.isBlank())
            return
        viewModelScope.launch {
            repo.addTask(Task(
                title = task.title,
                description = task.description,
                isCompleted = false
            ))
            _state.value = TaskState()

        }
    }

    fun updateTask(task : Task){
        val state = _state.value
        val updatedTask = task.copy(
            title = state.title,
            description = state.description
        )
        viewModelScope.launch {
            repo.updateTask(updatedTask)
            _state.value = _state.value.copy(title = "", description = "")
        }
    }

    fun onTitleChanged(name: String){
        _state.value = _state.value.copy(title = name)
    }

    fun onDescriptionChanged(description: String){
        _state.value = _state.value.copy(description = description)
    }

}


data class TaskState(
    val title : String = "",
    val description : String = "",
    val isCompleted : Boolean = false
)