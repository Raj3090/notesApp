package com.raj.notes.di.component

import com.raj.notes.di.FragmentScope
import com.raj.notes.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

//    fun inject(fragment: HomeFragment)
//
//    fun inject(fragment: ProfileFragment)
//
//    fun inject(fragment: PhotoFragment)
}