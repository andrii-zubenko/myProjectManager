package com.example.projemanag.ui.utils

import android.util.Log
import com.example.projemanag.models.Board
import com.example.projemanag.utils.Constants
import com.example.projemanag.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

object DataPrep {
    fun createBoardToBeDeleted(name: String) {
        val boardToBeDeleted = Board(
            name = name,
            assignedTo = arrayListOf("8A7iNZWE3WciRBWqdBizNVprhvD3")
        )
        Utils.countingIdlingResource.increment()
        FirebaseFirestore.getInstance().collection(Constants.BOARDS)
            .document()
            .set(boardToBeDeleted, SetOptions.merge())
            .addOnSuccessListener {
                Log.e("Data SetUp", "Board created successfully")
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { exception ->
                Log.e(
                    "Data SetUp",
                    "Error while creating a board.",
                    exception
                )
                Utils.countingIdlingResource.decrement()
            }
    }
}