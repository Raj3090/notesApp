package com.raj.notes.ui.notes.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.raj.notes.NotesApplication
import com.raj.notes.R
import com.raj.notes.databinding.ActivityNoteDetailsBinding
import com.raj.notes.di.component.DaggerActivityComponent
import com.raj.notes.di.module.ActivityModule
import javax.inject.Inject

class NoteDetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: NoteDetailsViewModel

    lateinit var binding:ActivityNoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencies();
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_note_details)
        binding.setLifecycleOwner(this)
        binding.viewModel=viewModel



        val noteId = intent?.extras?.getLong("Note")
        noteId?.let {
            viewModel.setCurrentNote(it)
        }

    }


    private fun setupDependencies() {
        DaggerActivityComponent.builder().
            applicationComponent((application as NotesApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build().inject(this)

    }


    override   fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_detail_menu, menu)
        return true
    }

    override   fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {
                viewModel.insertOrUpdateNote()
                finish()
                return false
            }

            R.id.delete -> {
                viewModel.deleteNote()
                finish()
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}
