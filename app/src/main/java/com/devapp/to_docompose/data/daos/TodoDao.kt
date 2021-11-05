package com.devapp.to_docompose.data.daos

import androidx.room.*
import com.devapp.to_docompose.data.models.ToDoTask
import com.devapp.to_docompose.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("select * from ${Constants.DB_TB_NAME} order by id ASC")
    fun getAllTasks(): Flow<List<ToDoTask>>

    @Query("select * from ${Constants.DB_TB_NAME} where id=:taskId")
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insetTask(task: ToDoTask)

    @Update
    suspend fun updateTask(task: ToDoTask)

    @Delete
    suspend fun deleteTask(task: ToDoTask)

    @Query("delete from ${Constants.DB_TB_NAME}")
    suspend fun deleteAllTask()

    @Query("select * from ${Constants.DB_TB_NAME} where title LIKE '%' || :searchQuery ||'%' or content LIKE '%' || :searchQuery ||'%'")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    @Query("select * from ${Constants.DB_TB_NAME} order by case when priority like 'L%' then 1 when priority like 'M%' then 2 when priority like 'H%' then 3 END")
    fun sortByLowPriority(): Flow<List<ToDoTask>>

    @Query("select * from ${Constants.DB_TB_NAME} order by case when priority like 'H%' then 1 when priority like 'M%' then 2 when priority like 'L%' then 3 END")
    fun sortByHighPriority(): Flow<List<ToDoTask>>
}