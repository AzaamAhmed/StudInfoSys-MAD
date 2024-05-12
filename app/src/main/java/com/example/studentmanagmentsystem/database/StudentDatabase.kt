package com.example.studentmanagmentsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentmanagmentsystem.ListFragment

@Database(entities = [student::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun getStudentDao():StudentDao

    companion object{
        @Volatile
        private var INSTANCE:StudentDatabase? = null

        fun getInstance(context: Context):StudentDatabase{
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }

    }

}