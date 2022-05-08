package com.abhishek.todoapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.abhishek.todoapp.model.Task
import com.abhishek.todoapp.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    val getAllTasks = toDoRepository.getAllTasks.cachedIn(viewModelScope)

    public fun deleteTask(task: Task) {
        viewModelScope.launch {
            toDoRepository.deleteTask(task)
        }
    }
}
