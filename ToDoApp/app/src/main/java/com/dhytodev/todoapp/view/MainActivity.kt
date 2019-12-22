package com.dhytodev.todoapp.view

import android.os.Bundle
import android.util.Log.w
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhytodev.todoapp.R
import com.dhytodev.todoapp.adapter.MainAdapter
import com.dhytodev.todoapp.model.Task
import com.dhytodev.todoapp.model.TaskViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val taskViewModel: TaskViewModel by viewModel()
    private var listT= arrayListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insertTask()
//        deleteById(2)
//        update("MDV Training", false, 1)
        observeTask()
        rv_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_main.adapter = MainAdapter(this, listT){
            Toast.makeText(this, "HASIL : $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertTask() {
        val listTF = listOf(true, false)
        for (i in 1..20){
            taskViewModel.addNewTask(Task(i,"Belajar $i Android", listTF.shuffled().take(1)[0]))
        }
    }

    private fun observeTask() {
        taskViewModel.getAllTasks().observe(this, Observer {
            if (it.isNotEmpty()) {
                it.map {
                    listT.add(it)
                    w("INIII", "HASILL : $it")
                }
            }
        })
    }

    private fun deleteById(id: Int) {
        taskViewModel.deleteById(id)
    }

    private fun update(task: String, completed: Boolean, id: Int) {
        taskViewModel.setUpdate(task, completed, id)
    }
}
