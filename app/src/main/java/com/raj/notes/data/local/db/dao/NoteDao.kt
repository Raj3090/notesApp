package com.raj.notes.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raj.notes.data.local.db.entity.DummyEntity
import com.raj.notes.data.local.db.entity.Note
import io.reactivex.Flowable
import io.reactivex.Observable


@Dao
interface NoteDao {

    @get:Query("SELECT * FROM note")
    val notes: Flowable<List<Note>>

    @Query("SELECT * FROM note WHERE id = :noteId")
    fun getNote(noteId: Long): Flowable<Note>

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE title LIKE :search " +
            "OR description LIKE :search "+"OR location LIKE :search")
    fun getDealsList(search: String): Observable<List<Note>>
}