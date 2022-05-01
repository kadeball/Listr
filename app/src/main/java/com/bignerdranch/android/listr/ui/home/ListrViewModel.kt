package com.bignerdranch.android.listr.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.listr.Person
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG = "ListrViewModel"

open class ListrViewModel : ViewModel() {

    // DECLARE VARIABLES
    private lateinit var firestore: FirebaseFirestore
    internal var listOfPeople: MutableLiveData<ArrayList<Person>> = MutableLiveData<ArrayList<Person>>()

    // INITIALIZER
    init {
        firestore = initFirestore()
    }

    // FIRESTORE FUNCTIONS

    private fun initFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    internal fun writeToFirestore(person: Person, collection: String) {
        Log.d(TAG, "In writeToFirestore()")
        // Write the data
        val docData = hashMapOf(
            "first" to person.first,
            "last" to person.last
        )
        firestore.collection(collection)
            .add(docData)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    internal fun listenToCollection(collection: String) {
        firestore.collection(collection).addSnapshotListener {
                snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            // IF we get to this line, there's a connection to the database
            if (snapshot != null) {
                // There are records in this collection
                val list = ArrayList<Person>()
                val documents = snapshot.documents
                documents.forEach {
                    val listPerson = it.toObject(Person::class.java)
                    list.add(listPerson!!)
                }
                listOfPeople.value = list
            }
        }
    }
}