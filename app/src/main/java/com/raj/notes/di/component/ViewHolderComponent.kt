package com.raj.notes.di.component

import com.raj.notes.di.ViewModelScope
import com.raj.notes.di.module.ViewHolderModule
import com.raj.notes.ui.notes.list.NotesListItemHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(holder: NotesListItemHolder)
}