package com.dhytodev.todoapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dhytodev.todoapp.R
import com.dhytodev.todoapp.model.Task
import com.dhytodev.todoapp.model.TaskViewModel
import kotlinx.android.synthetic.main.activity_add_edit.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TASK = "extra_task"
        const val REQUEST_EDIT = "request_edit"
    }

    var isEdit: Boolean = false
    var taskItem: Task? = null
    var id: Int? = 0
    private val taskViewModel: TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        btn_save.setOnClickListener(this)

        taskItem = intent.getParcelableExtra(EXTRA_TASK)
        isEdit = intent.getBooleanExtra(REQUEST_EDIT, false)
        initView()
        initToolbar()
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initView() {
        if (isEdit) {
            btn_save.text = getString(R.string.update)
            id = taskItem?.id
            ti_nametask.text = Editable.Factory.getInstance().newEditable(taskItem?.task)
            cbox_add.isChecked = taskItem?.completed ?: false
        }
    }

    override fun onClick(v: View?) {
        if (v == btn_save) {
            saveData()
        }
    }

    private fun saveData() {
        val taskName = ti_nametask.text.toString()
        val isCheck = cbox_add.isChecked
        if (TextUtils.isEmpty(taskName)) {
            Toast.makeText(this, "Data masih kosong", Toast.LENGTH_SHORT).show()
        } else {
            if (isEdit) editData(id, taskName, isCheck) else insertTask(taskName, isCheck)
        }
    }

    private fun editData(id: Int?, text: String, isCheck: Boolean) {
        try {
            taskViewModel.setUpdate(text, isCheck, id ?: 0)
            toastMsg("Sukses  update data")
            finish()
        } catch (err: Exception) {
            toastMsg("Gagal  update data")
        }
    }

    private fun insertTask(text: String, isCheck: Boolean) {
        try {
            taskViewModel.addNewTask(Task(null, text, isCheck))
            toastMsg("Sukses  tambah data")
            finish()
        } catch (err: Exception) {
            toastMsg("Gagal tambah data")
        }
    }

    private fun toastMsg(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
