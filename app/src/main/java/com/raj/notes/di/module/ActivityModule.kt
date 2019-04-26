package com.raj.notes.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.raj.notes.data.repository.NotesRepository
import com.raj.notes.utils.ViewModelProviderFactory
import com.raj.notes.data.repository.UserRepository
import com.raj.notes.ui.notes.details.NoteDetailsViewModel
import com.raj.notes.ui.notes.list.NoteListViewModel
import com.raj.notes.ui.notes.list.NotesAdapter
import com.raj.notes.ui.splash.SplashViewModel
import com.raj.notes.utils.network.NetworkHelper
import com.raj.notes.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: AppCompatActivity) {


    @Provides
    fun provideNotesDetailsViewModel(
        notesRepository: NotesRepository

    ): NoteDetailsViewModel =

        ViewModelProviders.of(activity, ViewModelProviderFactory(NoteDetailsViewModel::class) {

            NoteDetailsViewModel(notesRepository)

        }).get(NoteDetailsViewModel::class.java)


    @Provides
    fun provideNotesViewModel(
        notesRepository: NotesRepository,
        compositeDisposable: CompositeDisposable
    ): NoteListViewModel =

        ViewModelProviders.of(activity, ViewModelProviderFactory(NoteListViewModel::class) {

            NoteListViewModel(notesRepository, compositeDisposable)

        }).get(NoteListViewModel::class.java)



    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider, networkHelper: NetworkHelper,
        compositeDisposable: CompositeDisposable, userRepository: UserRepository
    ): SplashViewModel =

        ViewModelProviders.of(activity, ViewModelProviderFactory(SplashViewModel::class) {

            SplashViewModel(schedulerProvider, networkHelper, compositeDisposable, userRepository)

        }).get(SplashViewModel::class.java)


    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun providePostsAdapter(): NotesAdapter = NotesAdapter(ArrayList())




}