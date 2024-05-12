package com.example.studentmanagmentsystem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentmanagmentsystem.database.student

class ListFragmentData: ViewModel() {

    private val _data = MutableLiveData<List<student>>()

    val data:LiveData<List<student>> = _data

    fun setData(data:List<student>){
        _data.value = data
    }
}