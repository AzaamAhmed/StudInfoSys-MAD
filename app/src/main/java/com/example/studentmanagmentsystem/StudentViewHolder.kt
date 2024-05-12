package com.example.studentmanagmentsystem

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class StudentViewHolder(view: View):ViewHolder(view) {

    val stdName: TextView
    val stdID: TextView
    val stdNum: TextView
    val ivDelete: ImageView

    init {
        stdName = view.findViewById(R.id.txtName)
        stdID = view.findViewById(R.id.txtID)
        stdNum = view.findViewById(R.id.txtNum)
        ivDelete = view.findViewById(R.id.ivDelete)
    }
}