package com.example.gallery

import java.io.Serializable
import java.util.*

data class Image(
    var image: Int,
    var desc: String,
    var rating: Float
):Serializable