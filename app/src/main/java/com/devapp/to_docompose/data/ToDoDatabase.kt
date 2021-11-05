package com.devapp.to_docompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devapp.to_docompose.data.daos.TodoDao
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.util.Constants

@Database(
    entities = [ToDoTask::class],
    version = 1,
    exportSchema = false,
)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun getDao(): TodoDao
}