package com.example.studentmanagmentsystem.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class student(
    val stdId:String?,
    val name:String?,
    val number: String?

){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}
