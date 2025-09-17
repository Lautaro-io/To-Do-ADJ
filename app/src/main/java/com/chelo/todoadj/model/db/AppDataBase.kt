package com.chelo.todoadj.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chelo.todoadj.model.daos.TaskDao
import com.chelo.todoadj.model.entities.Task


@Database(entities = [Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}