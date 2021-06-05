package com.example.notifyme

import java.io.Serializable
import java.util.*

data class Notification(
    var name: String,
    var priority:Int,
    var image:Int,
    var date:Date
):Serializable