package com.dhytodev.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class], version = 1,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java, "todoapp.db"
                    ).allowMainThreadQueries()
                        .build()
                }
                return INSTANCE as TaskDatabase
            }

            return INSTANCE as TaskDatabase
        }
    }
}