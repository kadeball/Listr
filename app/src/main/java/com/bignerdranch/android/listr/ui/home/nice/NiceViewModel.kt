package com.bignerdranch.android.listr.ui.home.nice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.listr.Person
import com.bignerdranch.android.listr.ui.home.ListrViewModel
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG = "NiceFragment"

class NiceViewModel : ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    public lateinit var people: Array<Person>

    private val _text = MutableLiveData<String>().apply {
        value = "The Nice List"
    }

    init {
        firestore = initFirestore()
    }

    private fun initFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    internal fun writeToFirestore(person: Person) {
        Log.d(TAG, "In writeToFirestore")
        // Write the data
        val docData = hashMapOf(
            "first" to person.first,
            "last" to person.last
        )
        firestore.collection("nice")
            .add(docData)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    public fun getPeople(callback: (people: List<Person>) -> Unit) {
        firestore.collection("nice").get()
            .addOnSuccessListener { documents ->
                val people: List<Person> = documents.map { document ->
                    Person(document.data["first"] as String, document.data["last"] as String)
                }
                callback.invoke(people)
            }
            .addOnFailureListener { exception ->
                callback.invoke(listOf())
            }
    }

    val text: LiveData<String> = _text
}