package com.raj.notes.di.module

import androidx.recyclerview.widget.RecyclerView
import com.raj.notes.data.repository.UserRepository
import com.raj.notes.di.ViewModelScope
import com.raj.notes.ui.notes.list.NotesListItemViewModel
import com.raj.notes.utils.network.NetworkHelper
import com.raj.notes.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewHolderModule(private val viewHolder: RecyclerView.ViewHolder) {

    @ViewModelScope
    @Provides
    fun providePostItemViewModel(

    ): NotesListItemViewModel =
        NotesListItemViewModel(

        )
}