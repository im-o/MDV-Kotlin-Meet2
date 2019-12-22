package com.dhytodev.todoapp

import com.dhytodev.todoapp.db.TaskDatabase
import com.dhytodev.todoapp.model.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by rivaldy on 12/21/2019.
 */

val taskModule = module {
    single { TaskDatabase.getInstance(androidContext()) }
    viewModel { TaskViewModel(get()) }
}