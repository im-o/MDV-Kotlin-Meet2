package com.dhytodev.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dhytodev.todoapp.model.Task

@Database(
    entities = [Task::class], version = 2,
    exportSchema = false
)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private var INSTANCE: TaskDatabase? = null
        private val lock = Any()

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {}
        }

        fun getInstance(context: Context): TaskDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java, "todoapp.db"
                    )
                        .addMigrations(migration_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE as TaskDatabase
            }

            return INSTANCE as TaskDatabase
        }
    }
}