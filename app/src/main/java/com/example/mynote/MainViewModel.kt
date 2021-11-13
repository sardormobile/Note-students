package com.example.mynote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val lesson_add_name = MutableLiveData<String>()

}