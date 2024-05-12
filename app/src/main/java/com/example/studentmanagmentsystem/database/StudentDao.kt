package com.example.studentmanagmentsystem.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: student)

    @Delete
    suspend fun delete(student: student)

    @Query("SELECT * FROM student")
    fun getAllStudents():List<student>

    @Update
    suspend fun update(student: student)
}