package com.example.projemanag.ui.utils

import androidx.test.platform.app.InstrumentationRegistry
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date

fun dateInMills(): String {
    return SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
}

fun readJSONFromAsset(file: String): String? {
    val json: String?
    try {
        val inputStream =
            InstrumentationRegistry.getInstrumentation().context.assets.open(file)
        json = inputStream.bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
    return json
}

fun getJsonValue(value: String): String = JSONObject(readJSONFromAsset("creds.json"))
    .getString(value)
