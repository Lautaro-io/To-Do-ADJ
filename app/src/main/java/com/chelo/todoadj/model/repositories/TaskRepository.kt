package com.chelo.todoadj.model.repositories

import com.chelo.todoadj.model.daos.TaskDao
import com.chelo.todoadj.model.entities.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(val dao : TaskDao) {

    suspend fun addTask(task: Task) = dao.addTask(task)

    suspend fun deleteTask(task: Task) = dao.deleteTask(task)

    fun getAllTasks():Flow<List<Task>> = dao.getAllTasks()

    suspend fun updateTask(task: Task) = dao.updateTask(task)

    fun getTasksCompleted():Flow<List<Task>> = dao.getTasksCompleted()

    fun getTasksPending():Flow<List<Task>> = dao.getTasksPending()



}