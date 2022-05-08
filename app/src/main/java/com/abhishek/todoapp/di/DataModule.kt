package com.abhishek.todoapp.di

import android.app.Application
import androidx.room.Room
import com.abhishek.todoapp.data.TaskDao
import com.abhishek.todoapp.data.database.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): TaskDatabase {
        return Room
            .databaseBuilder(application, TaskDatabase::class.java, "Task.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }
}
