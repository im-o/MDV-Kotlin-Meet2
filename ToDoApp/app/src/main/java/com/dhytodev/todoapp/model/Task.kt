package com.dhytodev.todoapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tasks")
@Parcelize
data class Task(
    @PrimaryKey
    val id : Int,
    val task : String,
    val completed : Boolean = false
): Parcelable