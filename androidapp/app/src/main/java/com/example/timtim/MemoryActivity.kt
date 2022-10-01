package com.example.timtim

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timtim.databinding.MemoriesBinding

class MemoryActivity : AppCompatActivity() {

    private lateinit var binding: MemoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MemoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val eName = intent.getStringExtra("EventName") ?: ""
        val eLocation = intent.getStringExtra("EventLocation") ?: ""
        val eDate = intent.getStringExtra("EventDate") ?: ""
        val fFact = intent.getStringExtra("FunFact") ?: ""

        var items = mutableListOf<Item>(Item("test", "location", "date", "funfact"))

        if (eName != "") {
            items.add(Item(eName, eLocation, eDate, fFact))
        }

        val intent = Intent(this, QRCodeActivity::class.java)

        binding.recyclerView.adapter = ItemAdapter(items)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnAddNew.setOnClickListener {
            startActivity(intent)
        }
    }
}