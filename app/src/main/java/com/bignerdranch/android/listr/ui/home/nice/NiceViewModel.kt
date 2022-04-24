package com.bignerdranch.android.listr.ui.home.nice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NiceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is nice Fragment"
    }
    val text: LiveData<String> = _text
}