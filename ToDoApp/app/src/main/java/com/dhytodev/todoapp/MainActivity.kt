package com.dhytodev.todoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskDatabase: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskDatabase = TaskDatabase.getInstance(this)

        insertTasks()
        showTasks()
    }

    private fun addNewTask(task: Task): Long = taskDatabase.taskDao().insertTask(task)

    private fun getAllTasks(): List<Task> = taskDatabase.taskDao().getTasks()

    private fun insertTasks() {
        val task = Task(1, "Belajar Android")
        val task2 = Task(2, "Belajar Git", true)

        addNewTask(task)
        addNewTask(task2)
    }

    private fun showTasks() {
        val tasks = getAllTasks()

        for (todo in tasks) {
            Log.d("Task Name", todo.task)
            Log.d("Task Completed", todo.completed.toString())
        }
    }

}
