package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityUpadateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpadateNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpadateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root )

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId==-1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
             val newTitle = binding.updateTitleEditText.text.toString()
             val newContent = binding.updateContentEditText.text.toString()
            val upadateNote = Note(noteId, newTitle, newContent)
            db.updateNote(upadateNote)
            finish()
            Toast.makeText(this, "Changes Saved ", Toast.LENGTH_SHORT).show()
        }
    }
}