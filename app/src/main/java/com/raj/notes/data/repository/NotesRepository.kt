package com.raj.notes.data.repository

import com.raj.notes.data.model.User
import com.raj.notes.data.local.db.DatabaseService
import com.raj.notes.data.local.db.entity.Note
import com.raj.notes.data.remote.NetworkService
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {


    fun fetchNotesList(firstPostId: String?, lastPostId: String?): Flowable<List<Note>> {
        return databaseService.noteDao().notes
    }


    fun getNote(Id: Long): Flowable<Note> {
        return databaseService.noteDao().getNote(Id)
    }

    fun insertNote(note: Note) {
        return databaseService.noteDao().insertNote(note)
    }

    fun deleteNote(note: Note) {
        return databaseService.noteDao().deleteNote(note)
    }

    fun updateNote(note: Note) {
        return databaseService.noteDao().updateNote(note)
    }


}