package com.dhytodev.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id : Int,
    val task : String,
    val completed : Boolean = false
)