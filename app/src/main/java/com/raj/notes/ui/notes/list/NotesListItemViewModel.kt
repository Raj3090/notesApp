package com.raj.notes.ui.notes.list

import androidx.databinding.ObservableField
import com.raj.notes.data.local.db.entity.Note

class NotesListItemViewModel(var note: Note?=null) {

    lateinit var noteId: (Long) -> Unit

    fun getNoteTitle() = note?.run {
        return@run title
    }

     fun getNoteDescription() = note?.run {
        return@run description
    }

     fun getGeolocation() = note?.run {
        return@run location
    }

    fun onItemClick(){
        note?.id?.let {
          noteId(it)
        }
    }


    fun updateData(note: Note) {
        this.note = note
    }



}