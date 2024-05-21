package com.production.notessqllite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.production.notessqllite.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id", -1)

        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNodeByID(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {
            var newTitle = binding.updateTitleEditText.text.toString()
            var newContent = binding.updateContentEditText.text.toString()
            val updatedNote = Note(noteId, newTitle,newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this ,"Changed Saved!", Toast.LENGTH_SHORT).show()
        }

    }
}