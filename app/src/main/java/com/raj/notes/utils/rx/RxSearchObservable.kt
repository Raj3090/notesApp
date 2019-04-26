package com.raj.notes.utils.rx

import android.app.Activity
import android.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxSearchObservable {

    fun fromView(searchView: SearchView, context: Activity): Observable<String> {

        val subject = PublishSubject.create<String>()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

         override   fun onQueryTextSubmit(s: String): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return true
            }
        })

        return subject
    }


}// no instance