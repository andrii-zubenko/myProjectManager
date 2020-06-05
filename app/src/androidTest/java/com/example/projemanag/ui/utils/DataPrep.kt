package com.example.projemanag.ui.utils

import android.util.Log
import com.example.projemanag.models.Board
import com.example.projemanag.models.Task
import com.example.projemanag.utils.Constants
import com.example.projemanag.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

object DataPrep {
    fun createBoardToBeDeleted(boardName: String) {
        val boardToBeDeleted = Board(
            name = boardName,
            assignedTo = arrayListOf(getJsonValue("testUserID"))
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

    fun createTaskListToBeDeleted(taskListName: String) {
        val taskListToBeDeleted = Task(
            title = taskListName,
            createdBy = getJsonValue("testUserID")
        )
        Utils.countingIdlingResource.increment()
        FirebaseFirestore.getInstance().collection(Constants.BOARDS)
            .document(getJsonValue("testBoardID"))
            .get()
            .addOnSuccessListener { document ->
                val testBoard = document.toObject(Board::class.java)!!
                val currentTaskList = testBoard.taskList
                currentTaskList.add(0, taskListToBeDeleted)
                val taskListHashMap = HashMap<String, Any>()
                taskListHashMap[Constants.TASK_LIST] = currentTaskList
                Utils.countingIdlingResource.decrement()
                Utils.countingIdlingResource.increment()
                FirebaseFirestore.getInstance().collection(Constants.BOARDS)
                    .document(getJsonValue("testBoardID"))
                    .update(taskListHashMap)
                    .addOnSuccessListener {
                        Log.e("Data SetUp", "TaskList updated successfully.")
                        Utils.countingIdlingResource.decrement()
                    }.addOnFailureListener { exception ->
                        Log.e("Data SetUp", "Error while creating a board.", exception)
                        Utils.countingIdlingResource.decrement()
                    }
            }.addOnFailureListener { exception ->
                Log.e("Data SetUp", "Error while getting board details.", exception)
                Utils.countingIdlingResource.decrement()
            }
    }
}