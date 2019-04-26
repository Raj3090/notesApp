package com.raj.notes.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.raj.notes.NotesApplication
import com.raj.notes.R
import com.raj.notes.databinding.ActivitySplashBinding
import com.raj.notes.di.component.DaggerActivityComponent
import com.raj.notes.di.module.ActivityModule
import com.raj.notes.ui.notes.list.NoteListActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SplashViewModel

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        insertDependencies()
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_splash)
        binding.setLifecycleOwner(this)
        binding.viewModel=viewModel
        setupObservers()
        viewModel.onViewCreated()
    }

    private fun setupObservers() {

        viewModel.launchNoteList.observe(this, Observer {
            startActivity(Intent(applicationContext, NoteListActivity::class.java))
            finish()
        })

    }

    private fun insertDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as NotesApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build().
                inject(this)
    }
}
