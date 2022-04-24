package com.bignerdranch.android.listr.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.listr.Person
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel : ViewModel() {

    private lateinit var firestore: FirebaseFirestore
    private var person: Person? = null

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
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
        firestore.collection("test")
            .add(docData)

    }
}