package com.dhytodev.todoapp.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.dhytodev.todoapp.R
import com.dhytodev.todoapp.model.Task
import com.dhytodev.todoapp.view.AddEditActivity
import com.dhytodev.todoapp.view.AddEditActivity.Companion.EXTRA_TASK
import com.dhytodev.todoapp.view.AddEditActivity.Companion.REQUEST_EDIT
import kotlinx.android.synthetic.main.item_tasks.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by rivaldy on 12/22/2019.
 */

class MainAdapter(private val task: ArrayList<Task>, private val listener: (Task) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false))
    }

    override fun getItemCount() = task.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(task[position], listener)
    }

    class MainViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(task: Task, listener: (Task) -> Unit) {
            view.apply {
                tv_task.text = task.task
                tv_task.paintFlags = 0
                if (task.completed) {
                    tv_iscomplete.visibility = View.VISIBLE
                    tv_isnotcomplete.visibility = View.INVISIBLE
                } else {
                    tv_iscomplete.visibility = View.INVISIBLE
                    tv_isnotcomplete.visibility = View.VISIBLE
                    tv_task.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
            itemView.setOnClickListener {
                val popupMenu = PopupMenu(view.context, view)
                popupMenu.inflate(R.menu.option_task)
                popupMenu.setOnMenuItemClickListener { item: MenuItem? ->
                    when (item?.itemId) {
                        R.id.item_edit -> view.context.startActivity<AddEditActivity>(
                            EXTRA_TASK to task,
                            REQUEST_EDIT to true
                        )
                        R.id.item_delete -> listener(task)
                    }
                    true
                }
                popupMenu.show()
            }
        }
    }
}