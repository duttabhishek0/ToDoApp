package com.abhishek.todoapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0,
    var title: String = "",
    var content: String = "",
    var date: String = "",
    var background: Int = 0
) : Parcelable
