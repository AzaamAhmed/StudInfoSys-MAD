package com.example.studentmanagmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnStudentList: Button = findViewById(R.id.btnStdList)
        val btnStudentRegister: Button = findViewById(R.id.btnRegStd)

        btnStudentList.setOnClickListener{
            loadMainActivity("ListFragment")
        }
        btnStudentRegister.setOnClickListener {
            loadMainActivity("RegisterFragment")
        }
    }

    private fun loadMainActivity(fragmentIdentifier: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("selectedFragment", fragmentIdentifier)
        startActivity(intent)
    }
}
