package com.production.notessqllite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.production.notessqllite.databinding.ActivityAddNoteBinding

class AddNote_Activity : AppCompatActivity() {


    private lateinit var binding:ActivityAddNoteBinding
    private lateinit var db:NoteDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
//      THIS IS GOING TO STORE THE DATA
        db = NoteDatabaseHelper(this)
//      THIS LINE IS GOING TO SAVE THE NOTE ON CLICKING SAVEBUTTON

        binding.saveButton.setOnClickListener {
//            title is saved in this Variable
            val  title = binding.titleEditText.text.toString()
//            content saved in this variable
            val content = binding.contentEditText.text.toString()

            val note = Note(0, title , content)
            db.insertNote(note)
            finish()
            Toast.makeText(this , "Note Saved!",Toast.LENGTH_SHORT).show()
        }

    }
}