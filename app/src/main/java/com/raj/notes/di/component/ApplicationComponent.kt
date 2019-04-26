package com.raj.notes.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.raj.notes.NotesApplication
import com.raj.notes.data.local.db.DatabaseService
import com.raj.notes.data.remote.NetworkService
import com.raj.notes.data.repository.NotesRepository
import com.raj.notes.data.repository.UserRepository
import com.raj.notes.di.ApplicationContext
import com.raj.notes.utils.network.NetworkHelper
import com.raj.notes.utils.rx.SchedulerProvider
import com.raj.notes.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: NotesApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    /**
     * These methods are written in ApplicationComponent because the instance of
     * NetworkService is singleton and is maintained in the ApplicationComponent's implementation by Dagger
     * For NetworkService singleton instance to be accessible to say DummyActivity's DummyViewModel
     * this ApplicationComponent must expose a method that returns NetworkService instance
     * This method will be called when NetworkService is injected in DummyViewModel.
     * Also, in ActivityComponent you can find dependencies = [ApplicationComponent::class] to link this relationship
     */
    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    fun compositeDisposable(): CompositeDisposable

    fun schedulerProvider(): SchedulerProvider

    /**---------------------------------------------------------------------------
     * Dagger will internally create UserRepository instance using constructor injection.
     * Dependency through constructor
     * UserRepository ->
     *  [NetworkService -> Nothing is required],
     *  [DatabaseService -> Nothing is required],
     *  [UserPreferences -> [SharedPreferences -> provided by the function provideSharedPreferences in ApplicationModule class]]
     * So, Dagger will be able to create an instance of UserRepository by its own using constructor injection
     *---------------------------------------------------------------------------------
     */
    fun getUserRepository(): UserRepository
    fun getNoteRepository(): NotesRepository
}