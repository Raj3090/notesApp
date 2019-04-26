package com.raj.notes.ui.notes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raj.notes.data.local.db.entity.Note
import com.raj.notes.data.repository.NotesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteDetailsViewModel(val noteRepository: NotesRepository):ViewModel() {

    val titleField: MutableLiveData<String> = MutableLiveData()
    val valueField: MutableLiveData<String> = MutableLiveData()
    val locationField: MutableLiveData<String> = MutableLiveData()

    var currentNote:Note?=null

    fun insertOrUpdateNote(){
        if(currentNote!=null){
            updateNote()
        }else{
            val note = Note(null,titleField.value!!,valueField.value!!,locationField.value!!)
            noteRepository.insertNote(note)
        }

    }

    fun updateNote(){
        currentNote?.let {

            it.title=titleField.value!!
            it.description=valueField.value!!
            it.location=locationField.value!!

            noteRepository.updateNote(it)
        }

    }

    fun deleteNote(){
        currentNote?.let {
            noteRepository.deleteNote(it)
        }

    }


    fun setCurrentNote(id:Long){

        noteRepository.getNote(id).
            subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::ongetItem,this::onError)


    }

    fun onError(t:Throwable){

    }

    fun ongetItem(note:Note){
        currentNote=note
        titleField.value=currentNote?.title
        valueField.value=currentNote?.description
        locationField.value=currentNote?.location
    }




}