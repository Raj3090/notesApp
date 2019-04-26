package com.raj.notes.ui.splash

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raj.notes.data.repository.UserRepository
import com.raj.notes.utils.common.Event
import com.raj.notes.utils.network.NetworkHelper
import com.raj.notes.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    networkHelper: NetworkHelper,
    compositeDisposable: CompositeDisposable,
   val userRepository: UserRepository
) : ViewModel() {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchNoteList: MutableLiveData<Event<Bundle>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Bundle>> = MutableLiveData()



    fun onViewCreated() {
        // Empty Bundle passed to Activity in Event that is needed by the other Activity
        if (userRepository.getCurrentUser() == null)
            launchNoteList.postValue(Event(Bundle()))
        else
            launchLogin.postValue(Event(Bundle()))
    }

}