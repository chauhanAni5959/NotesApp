package com.production.notessqllite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes: List<Note>):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

        class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            var titleTextView:TextView = itemView.findViewById(R.id.titleTextView)
            var contentTextView:TextView = itemView.findViewById(R.id.contentTextView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int  = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text =  note.title
        holder.contentTextView.text = note.content
    }

    fun refreshData(newNotes: List<Note> ){
        notes = newNotes
        notifyDataSetChanged()

    }


}