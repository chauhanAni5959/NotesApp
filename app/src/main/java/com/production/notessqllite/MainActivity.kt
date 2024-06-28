package com.production.notessqllite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.production.notessqllite.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding

    private lateinit var db:NoteDatabaseHelper

    private lateinit var notesAdapter:NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        db = NoteDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNodes(), this)

        binding.notesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerview.adapter = notesAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNote_Activity::class.java);
            startActivity(intent);
        }

    }

//    We don't  want to refresh button so we override function
    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNodes() )
    }
}