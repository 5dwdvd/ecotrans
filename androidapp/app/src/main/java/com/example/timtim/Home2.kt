package com.example.timtim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.timtim.databinding.ActivityHome2Binding

private lateinit var binding: ActivityHome2Binding

class Home2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = Intent(this, Home3::class.java)

        binding.btnNxt.setOnClickListener {
            startActivity(intent)
        }
    }
}