package com.raj.notes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raj.notes.data.local.db.dao.NoteDao
import com.raj.notes.data.local.db.entity.Note
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        Note::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}