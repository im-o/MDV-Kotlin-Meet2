package com.dhytodev.todoapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dhytodev.todoapp.R
import com.dhytodev.todoapp.model.Task
import kotlinx.android.synthetic.main.item_tasks.view.*

/**
 * Created by rivaldy on 12/22/2019.
 */

class MainAdapter(private val context: Context, private val task: ArrayList<Task>, private val listener: (Task) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false))
    }

    override fun getItemCount() = task.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(task[position], listener)
//        holder.itemView.setOnClickListener {
//            Toast.makeText(context, "HASILLL : ${task[position]}", Toast.LENGTH_SHORT).show()
////            Log.w("INIII","HASILLL : ${task[position]}")
//        }
    }

    class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(task: Task, listener: (Task) -> Unit) {
            val strYes = "Completed"
            val strNo = "Not Complete"
            view.tv_task.text = task.task
            if (task.completed) view.tv_iscompleted.text = strYes else view.tv_iscompleted.text = strNo

            itemView.setOnClickListener { listener(task) }
        }
    }
}