package com.dhytodev.todoapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dhytodev.todoapp.db.TaskDatabase

/**
 * Created by rivaldy on 12/21/2019.
 */

class TaskViewModel(private val database: TaskDatabase) : ViewModel() {
    fun addNewTask(task: Task) = database.taskDao().insertTask(task)
    fun deleteById(id: Int) = database.taskDao().deleteById(id)
    fun setUpdate(task: String, completed: Boolean, id: Int) = database.taskDao().setUpdate(task, completed, id)
    fun getAllTasks(): LiveData<List<Task>> = database.taskDao().getTasks()
}