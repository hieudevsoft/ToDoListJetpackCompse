package com.devapp.to_docompose.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.devapp.to_docompose.util.Constants

@Entity(
    tableName = Constants.DB_TB_NAME,
    indices = arrayOf(Index(value = arrayOf("id"), unique = true)),
)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String,
    val content:String,
    val priority: Priority
)
