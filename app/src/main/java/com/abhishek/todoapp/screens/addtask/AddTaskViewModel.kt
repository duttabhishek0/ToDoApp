package com.abhishek.todoapp.screens.addtask

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.todoapp.model.Task
import com.abhishek.todoapp.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private val dateFormat = SimpleDateFormat("dd-MMM-yy hh:mm aa", Locale.UK)
    private val formattedDate: String = dateFormat.format(Date()).toString()

    public fun addTask(task: Task) {
        viewModelScope.launch {
            val random = Random()
            task.background =
                Color.argb(255, random.nextInt(255), random.nextInt(256), random.nextInt(256))
            task.date = formattedDate
            toDoRepository.addTask(task)
        }
    }

    public fun deleteTask(task: Task) {
        viewModelScope.launch {
            toDoRepository.deleteTask(task)
        }
    }

    public fun updateTask(task: Task) {
        viewModelScope.launch {
            toDoRepository.updateTask(task)
        }
    }
}
