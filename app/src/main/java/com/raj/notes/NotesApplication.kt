package com.raj.notes

import android.app.Application
import com.raj.notes.di.component.ApplicationComponent
import com.raj.notes.di.component.DaggerApplicationComponent
import com.raj.notes.di.module.ApplicationModule

class NotesApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}