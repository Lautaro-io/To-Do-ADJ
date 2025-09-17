package com.chelo.todoadj.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.chelo.todoadj.model.entities.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao  {

    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>


    @Query("SELECT * FROM tasks WHERE isCompleted = 1")
    fun getTasksCompleted():Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0")
    fun getTasksPending():Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)



}