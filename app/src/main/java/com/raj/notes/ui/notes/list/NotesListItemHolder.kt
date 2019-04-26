package com.raj.notes.ui.notes.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.raj.notes.NotesApplication
import com.raj.notes.data.local.db.entity.Note
import com.raj.notes.databinding.NotesListItemBinding
import com.raj.notes.di.component.DaggerViewHolderComponent
import com.raj.notes.di.module.ViewHolderModule
import com.raj.notes.ui.notes.details.NoteDetailsActivity
import com.raj.notes.utils.display.Toaster
import javax.inject.Inject

class NotesListItemHolder(val binding: NotesListItemBinding) : RecyclerView.ViewHolder(binding.root){

    @Inject
    lateinit var viewModel: NotesListItemViewModel

    init {
          DaggerViewHolderComponent.builder()
              .applicationComponent((binding.root.context.applicationContext as NotesApplication).applicationComponent)
              .viewHolderModule(ViewHolderModule(this))
              .build().inject(this)

        viewModel.noteId={
            val intent = Intent(binding.root.context, NoteDetailsActivity::class.java)
            intent.putExtra("Note",it)
            binding.root.context.startActivity(intent)
        }



    }



    fun bind(note: Note) {
        viewModel.updateData(note)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

}