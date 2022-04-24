package com.bignerdranch.android.listr.ui.home.nice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bignerdranch.android.listr.R

class NiceFragment : Fragment() {

    private lateinit var niceViewModel: NiceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        niceViewModel =
            ViewModelProviders.of(this).get(niceViewModel::class.java)
        val view = inflater.inflate(R.layout.nice_fragment, container, false)
        val textView: TextView = view.findViewById(R.id.nice_text)
        niceViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }
}