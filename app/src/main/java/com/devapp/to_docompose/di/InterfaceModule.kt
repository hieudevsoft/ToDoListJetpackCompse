package com.devapp.to_docompose.di

import com.devapp.to_docompose.data.repositories.ToDoRepoApp
import com.devapp.to_docompose.data.repositories.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {
    @Singleton
    @Binds
    abstract fun bindToDoRepository(toDoRepository: ToDoRepository):ToDoRepoApp
}