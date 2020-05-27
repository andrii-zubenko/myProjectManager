package com.example.projemanag.ui.utils

import java.text.SimpleDateFormat
import java.util.Date

fun dateInMills(): String {
    return SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
}
