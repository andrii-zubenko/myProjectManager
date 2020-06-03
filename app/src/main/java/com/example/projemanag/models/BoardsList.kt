package com.example.projemanag.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BoardsList(
    @Expose
    @SerializedName("documents")
    val list: ArrayList<Board>? = null
) {
    override fun toString(): String {
        return "Results($list)"
    }
}