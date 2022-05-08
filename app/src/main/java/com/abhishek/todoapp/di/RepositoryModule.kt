package com.abhishek.todoapp.di

import com.abhishek.todoapp.data.TaskDao
import com.abhishek.todoapp.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        taskDao: TaskDao
    ): ToDoRepository {
        return ToDoRepository(taskDao)
    }
}
