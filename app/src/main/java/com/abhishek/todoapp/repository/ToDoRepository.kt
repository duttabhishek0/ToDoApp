package com.abhishek.todoapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.abhishek.todoapp.data.TaskDao
import com.abhishek.todoapp.model.Task
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    val getAllTasks = Pager(
        config = PagingConfig(pageSize = 5),
        pagingSourceFactory = { taskDao.getTaskList() }
    ).flow

    suspend fun addTask(task: Task) {
        taskDao.insertTask(task = task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task = task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task = task)
    }
}
