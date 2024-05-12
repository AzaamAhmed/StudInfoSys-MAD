package com.example.studentmanagmentsystem.database

class StudentRepository(private val db:StudentDatabase) {

    suspend fun insert(student: student) = db.getStudentDao().insert(student)
    suspend fun delete(student: student) = db.getStudentDao().delete(student)

    fun getAllStudents():List<student> = db.getStudentDao().getAllStudents()

}