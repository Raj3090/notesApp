package com.raj.notes.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "note")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Long?=null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    @NotNull
    var description: String,

    @ColumnInfo(name = "location")
    var location: String
){
    constructor():this(null,"","",""
        )
}