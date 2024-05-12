package com.example.studentmanagmentsystem

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.studentmanagmentsystem.database.StudentRepository
import com.example.studentmanagmentsystem.database.student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StudentAdapter(context: Context,items:List<student>, repository: StudentRepository,
                     viewModel: ListFragmentData):Adapter<StudentViewHolder>(){

    private var context = context
    private val items = items
    private val repository = repository
    private val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_students,parent,false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {

        holder.stdName.text= items.get(position).name
        holder.stdID.text= items.get(position).stdId
        holder.stdNum.text= items.get(position).number.toString()

        holder.ivDelete.setOnClickListener{
           deleteDialog("Alert","do you want to delete",position)
        }
    }

    fun deleteDialog(title: String, message: String,position: Int) {

        val builder = AlertDialog.Builder(context)
        // Set the alert dialog title and message
        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("OK") { dialog, which ->
            CoroutineScope(Dispatchers.IO).launch {
                repository.delete(items.get(position))
                val data = repository.getAllStudents()
                withContext(Dispatchers.Main) {
                    viewModel.setData(data)
                    Toast.makeText(
                        context,
                        "Student ${items.get(position).name} successfully deleted",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}

