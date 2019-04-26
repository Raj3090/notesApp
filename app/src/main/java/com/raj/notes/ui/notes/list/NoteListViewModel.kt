package com.raj.notes.ui.notes.list

import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raj.notes.data.local.db.entity.Note
import com.raj.notes.data.repository.NotesRepository
import com.raj.notes.utils.common.Event
import com.raj.notes.utils.common.LoadMoreListener
import com.raj.notes.utils.common.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import timber.log.Timber

class NoteListViewModel(val noteRepository: NotesRepository,val compositeDisposable:CompositeDisposable ):ViewModel() {


    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    val post: MutableLiveData<Resource<List<Note>>> = MutableLiveData<Resource<List<Note>>>()
    val loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private val allPostList = ArrayList<Note>()

    val launchAddNote: MutableLiveData<Resource<String>> = MutableLiveData()

    init {

        noteRepository.fetchNotesList("","").
            subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::ongetItems,this::onError)

    }

    fun onError(t:Throwable){

    }

    fun ongetItems(list:List<Note>){
        post.postValue(Resource.success(list))
    }

    fun addNote(){
        launchAddNote.postValue(Resource.success("AddNote"))
    }


}