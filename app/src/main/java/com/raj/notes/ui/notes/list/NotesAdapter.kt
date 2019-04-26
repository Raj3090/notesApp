package com.raj.notes.ui.notes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raj.notes.data.local.db.entity.Note
import com.raj.notes.databinding.NotesListItemBinding

class NotesAdapter(val notes:ArrayList<Note>):RecyclerView.Adapter<NotesListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListItemHolder =
        NotesListItemHolder(
            NotesListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    fun appendData(posts: List<Note>) {
        val oldCount = itemCount
        this.notes.removeAll(notes)
        this.notes.addAll(posts)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount > 0 && currentCount > oldCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    override fun getItemCount()=notes.size

    override fun onBindViewHolder(holder: NotesListItemHolder, position: Int) {
       holder.bind(notes.get(position))
    }


}