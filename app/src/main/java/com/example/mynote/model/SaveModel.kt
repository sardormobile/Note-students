package com.example.mynote.model

import android.graphics.Bitmap


data class SaveModel(
    val data: String,
    val image: Bitmap?,
    val time: String,
)
data class SaveTextModel(
    val text: String?
)