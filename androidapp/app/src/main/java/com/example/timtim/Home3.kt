package com.example.timtim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.timtim.databinding.ActivityHome3Binding

private lateinit var binding: ActivityHome3Binding

class Home3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Ezra", "Got here")
        binding = ActivityHome3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = Intent(this, MemoryActivity::class.java)

        binding.btnNxt.setOnClickListener {
            startActivity(intent)
        }
    }
}