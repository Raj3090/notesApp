package com.raj.notes.di.component

import com.raj.notes.di.ActivityScope
import com.raj.notes.di.module.ActivityModule
import com.raj.notes.ui.notes.details.NoteDetailsActivity
import com.raj.notes.ui.notes.list.NoteListActivity
import com.raj.notes.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent{


    fun inject(noteListActivity: NoteListActivity)
    fun inject(splashActivity: SplashActivity)
    fun inject(notesDetailsActivity: NoteDetailsActivity)



}