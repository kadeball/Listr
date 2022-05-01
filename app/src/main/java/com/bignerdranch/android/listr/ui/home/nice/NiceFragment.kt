package com.bignerdranch.android.listr.ui.home.nice

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

private const val TAG = "NiceFragment"

class NiceFragment : Fragment() {
    private lateinit var niceRecyclerView: RecyclerView
    private lateinit var niceViewModel: NiceViewModel
    private lateinit var adapter: PersonAdapter
    private val peopleListModel = PeopleListModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        niceViewModel =
            ViewModelProviders.of(this).get(NiceViewModel::class.java)
        val view = inflater.inflate(R.layout.nice_fragment, container, false)
        niceRecyclerView = view.findViewById(R.id.fragment_nice) as RecyclerView
        niceRecyclerView.layoutManager = LinearLayoutManager(context)

        val fab: FloatingActionButton? = activity?.findViewById(R.id.fab)
        fab?.setOnClickListener { view ->
            val person = peopleListModel.people.shuffled().take(1)[0]
            niceViewModel.writeToFirestore(person)
            updateUI()
        }

        updateUI()

        return view
    }

    private fun updateUI() {
        niceViewModel.getPeople(callback = { people ->
            adapter = PersonAdapter(people)
            niceRecyclerView.adapter = adapter
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
            val nicePerson = people[position]
            holder.apply {
                personName.text = nicePerson.first + " " + nicePerson.last
            }
        }
    }
}