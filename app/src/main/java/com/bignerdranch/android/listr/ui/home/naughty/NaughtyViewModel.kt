package com.bignerdranch.android.listr.ui.home.naughty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.listr.Person
import com.google.firebase.firestore.FirebaseFirestore

class NaughtyViewModel : ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    private var person: Person? = null

    private val _text = MutableLiveData<String>().apply {
        value = "This is the naughty Fragment"
    }
    val text: LiveData<String> = _text

    init {
        firestore = initFirestore()
    }

    private fun initFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    internal fun writeToFirestore(person: Person) {

        // Write the data
        val docData = hashMapOf(
            "first" to person.first,
            "last" to person.last
        )
        firestore.collection("naughty")
            .add(docData)
    }
}