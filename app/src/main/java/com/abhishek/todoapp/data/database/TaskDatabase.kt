package com.abhishek.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhishek.todoapp.data.TaskDao
import com.abhishek.todoapp.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
