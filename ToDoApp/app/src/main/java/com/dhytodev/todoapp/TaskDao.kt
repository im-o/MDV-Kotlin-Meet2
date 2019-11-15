package com.dhytodev.todoapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task) : Long

    @Query("SELECT * FROM tasks")
    fun getTasks() : List<Task>
}