package com.bignerdranch.android.listr.ui.home.naughty

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bignerdranch.android.listr.Person
import com.bignerdranch.android.listr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NaughtyFragment : Fragment() {

    private lateinit var naughtyviewModel: NaughtyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        naughtyviewModel =
            ViewModelProviders.of(this).get(NaughtyViewModel::class.java)
        val view = inflater.inflate(R.layout.naughty_fragment, container, false)
        val textView: TextView = view.findViewById(R.id.naughty_text)
        naughtyviewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val fab: FloatingActionButton? = activity?.findViewById(R.id.fab)
        fab?.setOnClickListener { view ->
            var person = Person("Nigel", "IsNaughty")
            naughtyviewModel.writeToFirestore(person)
        }

        return view
    }
}