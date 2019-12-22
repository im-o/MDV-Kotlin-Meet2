package com.dhytodev.todoapp

import android.annotation.SuppressLint
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by rivaldy on 12/21/2019.
 */

@SuppressLint("Registered")
class TodoApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TodoApp)
            modules(taskModule)
        }
    }
}