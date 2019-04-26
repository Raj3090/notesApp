package com.raj.notes.ui.notes.list

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.raj.notes.NotesApplication
import com.raj.notes.R
import com.raj.notes.data.local.db.DatabaseService
import com.raj.notes.data.local.db.entity.Note
import com.raj.notes.databinding.ActivityNoteListBinding
import com.raj.notes.di.component.DaggerActivityComponent
import com.raj.notes.di.module.ActivityModule
import com.raj.notes.ui.notes.details.NoteDetailsActivity
import com.raj.notes.utils.rx.RxSearchObservable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_note_list.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NoteListActivity : AppCompatActivity() {

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var notessAdapter: NotesAdapter

    @Inject
    lateinit var viewModel: NoteListViewModel

    lateinit var binding : ActivityNoteListBinding

    @Inject
    lateinit var dataBase:DatabaseService

    override fun onCreate(savedInstanceState: Bundle?) {
        insertDependencies()
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_note_list)
        binding.setLifecycleOwner(this)
        binding.viewModel=viewModel
        setUpView()
        setUpObsever()


    }

    private fun setUpView() {
        noteList.layoutManager=linearLayoutManager
        noteList.adapter=notessAdapter

    }

    private fun setUpObsever() {

        viewModel.post.observe(this, Observer {
            it.data?.run {
                notessAdapter.appendData(this)
            }
        })

        viewModel.launchAddNote.observe(this, Observer {
            val intent = Intent(applicationContext, NoteDetailsActivity::class.java)
            startActivity(intent)
        })
    }

    private fun insertDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NotesApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)


    }

    override   fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_search_menu, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        val subscribe = RxSearchObservable.fromView(searchView, this)
            .debounce(300, TimeUnit.MILLISECONDS)
            .filter(Predicate<String> { text -> text.length > 0 })
            .distinctUntilChanged()
            .switchMap(Function<String, ObservableSource<List<Note>>> { query ->
                dataBase.noteDao().getDealsList(query).onErrorResumeNext(
                    Function<Throwable, ObservableSource<out List<Note>>> { Observable.just(ArrayList()) })
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<List<Note>> { result -> notessAdapter.appendData(result) })

        return true
    }

    override   fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {

                return false
            }

        }
        return super.onOptionsItemSelected(item)
    }



}
