package com.projetos.memorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.Navigation
import com.projetos.core.data.Note
import com.projetos.memorynotes.R
import com.projetos.memorynotes.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        btnCheck.setOnClickListener {
            if (et_title.text.toString() != "" || et_content.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = et_title.text.toString()
                currentNote.content = et_content.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creatrionTime = time
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
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_title.windowToken, 0)
    }

}