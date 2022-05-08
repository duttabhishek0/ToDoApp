package com.abhishek.todoapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abhishek.todoapp.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskList(taskList: List<Task>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task")
    fun getTaskList(): PagingSource<Int, Task>

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    abstract fun updateTask(task: Task)
}
