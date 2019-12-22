package com.dhytodev.todoapp.view

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhytodev.todoapp.R
import com.dhytodev.todoapp.adapter.MainAdapter
import com.dhytodev.todoapp.model.Task
import com.dhytodev.todoapp.model.TaskViewModel
import com.dhytodev.todoapp.view.AddEditActivity.Companion.REQUEST_EDIT
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val taskViewModel: TaskViewModel by viewModel()
    private var listTask = arrayListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeTask()
        initView()
    }

    private fun initView() {
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = MainAdapter(listTask) { showDialog(it.id) }
        fab_main.setOnClickListener { startActivity<AddEditActivity>(REQUEST_EDIT to false) }
    }

    private fun observeTask() {
        taskViewModel.getAllTasks().observe(this, Observer {
            if (it.isNotEmpty()) {
                listTask.clear()
                listTask.addAll(it)
                rv_main.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun deleteById(id: Int) {
        try {
            taskViewModel.deleteById(id)
            Toast.makeText(this, "Sukses hapus data", Toast.LENGTH_SHORT).show()
        } catch (err: Exception) {
            Toast.makeText(this, "Gagal hapus data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog(id: Int?) {
        val builder = AlertDialog.Builder(this, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
            .setTitle(R.string.delete)
            .setMessage(R.string.msgDelete)
            .setIcon(R.drawable.ic_delete_forever_black_24dp)
            .setPositiveButton(android.R.string.yes) { dialog, which ->
                deleteById(id!!)
            }
            .setNegativeButton(android.R.string.no, null)
            .create()
        builder.show()
    }
}
