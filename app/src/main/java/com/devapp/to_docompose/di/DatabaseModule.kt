package com.devapp.to_docompose.di

import android.content.Context
import androidx.room.Room
import com.devapp.to_docompose.data.ToDoDatabase
import com.devapp.to_docompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context)
    = Room.databaseBuilder(context,ToDoDatabase::class.java,Constants.DB_NAME)
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(database: ToDoDatabase) = database.getDao()
}