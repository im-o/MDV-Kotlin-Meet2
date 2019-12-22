package com.dhytodev.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dhytodev.todoapp.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task): Long

    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("DELETE FROM tasks WHERE id = :id")
    fun deleteById(id: Int);

    @Delete
    fun deleteAll(task: Task)

    @Query("DELETE FROM tasks")
    fun clearTask()

    @Query("UPDATE tasks set task=:task, completed=:completed WHERE id=:id")
    fun setUpdate(task: String, completed: Boolean, id: Int)
}