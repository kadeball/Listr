package com.bignerdranch.android.listr.ui.home.naughty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NaughtyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is naughty Fragment"
    }
    val text: LiveData<String> = _text
}