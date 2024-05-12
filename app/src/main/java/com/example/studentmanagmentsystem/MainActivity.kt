package com.example.studentmanagmentsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.studentmanagmentsystem.RegisterFragment
import com.example.studentmanagmentsystem.ListFragment

class MainActivity : AppCompatActivity() {
    private val registerFragment = RegisterFragment()
    private val listFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnList: Button = findViewById(R.id.btnList)
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnHome: ImageView = findViewById(R.id.ivHome)

        btnHome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val fragmentIdentifier= intent.getStringExtra("selectedFragment")

        if (fragmentIdentifier != null) {
            when (fragmentIdentifier) {
                "ListFragment" -> loadFragment(listFragment)
                "RegisterFragment" -> loadFragment(registerFragment)

                else -> loadList()
            }
        }else
            loadList()

        btnList.setOnClickListener() {
            loadList()
        }

        btnAdd.setOnClickListener() {
            loadRegister()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
        }
    }

    private fun loadRegister() {
        loadFragment(registerFragment)
    }

    private fun loadList() {
        loadFragment(listFragment)
    }
}
