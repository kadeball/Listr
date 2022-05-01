package com.bignerdranch.android.listr.ui.home.naughty

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.listr.PeopleListModel
import com.bignerdranch.android.listr.Person
import com.bignerdranch.android.listr.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val TAG = "NaughtyFragment"

class NaughtyFragment : Fragment() {

    private lateinit var naughtyRecyclerView: RecyclerView
    private lateinit var naughtyViewModel: NaughtyViewModel
    private lateinit var adapter: PersonAdapter
    private val peopleListModel = PeopleListModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        naughtyViewModel =
            ViewModelProviders.of(this).get(NaughtyViewModel::class.java)
        val view = inflater.inflate(R.layout.naughty_fragment, container, false)
        naughtyRecyclerView = view.findViewById(R.id.fragment_naughty) as RecyclerView
        naughtyRecyclerView.layoutManager = LinearLayoutManager(context)

        val fab: FloatingActionButton? = activity?.findViewById(R.id.fab)
        fab?.setOnClickListener { view ->
            val person = peopleListModel.people.shuffled().take(1)[0]
            naughtyViewModel.writeToFirestore(person)
            updateUI()
        }

        updateUI()

        return view
    }

    private fun updateUI() {
        naughtyViewModel.getPeople(callback = {
            adapter = PersonAdapter(it)
            naughtyRecyclerView.adapter = adapter
        })
    }

    private inner class PersonHolder(view: View): RecyclerView.ViewHolder(view) {
        val personName: TextView = view.findViewById(R.id.person)
    }

    private inner class PersonAdapter(var people: List<Person>) : RecyclerView.Adapter<PersonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
            val view = layoutInflater.inflate(R.layout.list_item_person, parent, false)
            view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            return PersonHolder(view)
        }
        override fun getItemCount() = people.size
        override fun onBindViewHolder(holder: PersonHolder, position: Int) {
            val naughtyPerson = people[position]
            holder.apply {
                personName.text = naughtyPerson.first + " " + naughtyPerson.last
            }
        }
    }
}