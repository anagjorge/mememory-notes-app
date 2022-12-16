package com.projetos.memorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.Navigation
import com.projetos.core.data.Note
import com.projetos.memorynotes.R
import com.projetos.memorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if(noteId != 0L){
            viewModel.getNote(noteId)
        }


        btnCheck.setOnClickListener {
            if (et_title.text.toString() != "" || et_content.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = et_title.text.toString()
                currentNote.content = et_content.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            saved.observe(viewLifecycleOwner, Observer{
                if(it){
                    Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                    hideKeyboard()
                    Navigation.findNavController(et_title).popBackStack()
                } else {
                    Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
                }
            })
        }
        viewModel.currentNote.observe(viewLifecycleOwner, Observer{ note ->
            note?.let {
                currentNote = it
                et_title.setText(it.title, TextView.BufferType.EDITABLE)
                et_content.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_title.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.deleteNote -> {
                if(context != null && noteId != 0L) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete note")
                        .setMessage("Are you sure want delete this note?")
                        .setPositiveButton("Yes") { dialogInterface, i ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Cancel ") { dialogInterface, i -> }
                        .create()
                        .show()

                }
            }

        }
        return true
    }


}