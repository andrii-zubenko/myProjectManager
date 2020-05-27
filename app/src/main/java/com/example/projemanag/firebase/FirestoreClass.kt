package com.example.projemanag.firebase

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.example.projemanag.activities.CardDetailsActivity
import com.example.projemanag.activities.CreateBoardActivity
import com.example.projemanag.activities.MainActivity
import com.example.projemanag.activities.MembersActivity
import com.example.projemanag.activities.MyProfileActivity
import com.example.projemanag.activities.SignInActivity
import com.example.projemanag.activities.SignUpActivity
import com.example.projemanag.activities.TaskListActivity
import com.example.projemanag.models.Board
import com.example.projemanag.models.User
import com.example.projemanag.utils.Constants
import com.example.projemanag.utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { e ->
                Log.e(activity.javaClass.simpleName, "Error", e)
                Utils.countingIdlingResource.decrement()
            }
    }

    fun getBoardDetails(activity: TaskListActivity, documentId: String) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.BOARDS)
            .document(documentId)
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())
                val board = document.toObject(Board::class.java)!!
                board.documentID = document.id
                activity.boardDetails(board)
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { exception ->
                activity.hideProgressDialog()
                Log.i(activity.javaClass.simpleName, "Error while creating a board.", exception)
                Utils.countingIdlingResource.decrement()
            }
    }

    fun createBoard(activity: CreateBoardActivity, board: Board) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.BOARDS)
            .document()
            .set(board, SetOptions.merge())
            .addOnSuccessListener {
                Log.e(activity.javaClass.simpleName, "Board created successfully")
                Toast.makeText(
                    activity,
                    "Board created successfully.",
                    Toast.LENGTH_SHORT
                ).show()
                activity.boardCreatedSuccessfully()
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { exception ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while creating a board.",
                    exception
                )
                Utils.countingIdlingResource.decrement()
            }
    }

    fun getBoardsList(activity: MainActivity) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.BOARDS)
            .whereArrayContains(Constants.ASSIGNED_TO, getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.documents.toString())
                val boardsList: ArrayList<Board> = ArrayList()
                for (i in document) {
                    val board = i.toObject(Board::class.java)
                    board.documentID = i.id
                    boardsList.add(board)
                }
                activity.populateBoardsListToUI(boardsList)
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { exception ->
                activity.hideProgressDialog()
                Log.i(activity.javaClass.simpleName, "Error while creating a board.", exception)
                Utils.countingIdlingResource.decrement()
            }
    }

    fun addUpdateTaskList(activity: Activity, board: Board) {
        Utils.countingIdlingResource.increment()
        val taskListHashMap = HashMap<String, Any>()
        taskListHashMap[Constants.TASK_LIST] = board.taskList
        mFireStore.collection(Constants.BOARDS)
            .document(board.documentID)
            .update(taskListHashMap)
            .addOnSuccessListener {
                Log.e(activity.javaClass.simpleName, "TaskList updated successfully.")
                if (activity is TaskListActivity) {
                    activity.addUpdateTaskListSuccess()
                } else if (activity is CardDetailsActivity) {
                    activity.addUpdateTaskListSuccess()
                }
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { exception ->
                if (activity is TaskListActivity) {
                    activity.hideProgressDialog()
                } else if (activity is CardDetailsActivity) {
                    activity.hideProgressDialog()
                }
                Log.e(activity.javaClass.simpleName, "Error while creating a board.", exception)
                Utils.countingIdlingResource.decrement()
            }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                Log.i(activity.javaClass.simpleName, "Profile Data updated successfully!")
                Toast.makeText(activity, "Profile update successfully!", Toast.LENGTH_LONG).show()
                when (activity) {
                    is MainActivity -> {
                        activity.tokenUpdateSuccess()
                    }
                    is MyProfileActivity -> {
                        activity.profileUpdateSuccess()
                    }
                }
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { exception ->
                when (activity) {
                    is MainActivity -> {
                        activity.hideProgressDialog()
                    }
                    is MyProfileActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error while creating a board", exception)
                Toast.makeText(activity, "Error when updating profile!", Toast.LENGTH_LONG).show()
                Utils.countingIdlingResource.decrement()
            }
    }

    fun loadUserData(activity: Activity, readBoardsList: Boolean = false) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)!!

                when (activity) {
                    is SignInActivity -> {
                        activity.signInSuccess()
                    }
                    is MainActivity -> {
                        activity.updateNavigationUserDetails(loggedInUser, readBoardsList)
                    }
                    is MyProfileActivity -> {
                        activity.setUserDataInUI(loggedInUser)
                    }
                }
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { e ->
                when (activity) {
                    is SignInActivity -> {
                        activity.hideProgressDialog()
                    }
                    is MainActivity -> {
                        activity.hideProgressDialog()
                    }
                }
                Log.e(activity.javaClass.simpleName, "Error writing document", e)
                Utils.countingIdlingResource.decrement()
            }
    }

    fun getAssignedMembersListDetails(activity: Activity, assignedTo: ArrayList<String>) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.USERS)
            .whereIn(Constants.ID, assignedTo)
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.documents.toString())
                val usersList: ArrayList<User> = ArrayList()

                for (i in document.documents) {
                    val user = i.toObject(User::class.java)!!
                    usersList.add(user)
                }
                if (activity is MembersActivity) {
                    activity.setupMembersList(usersList)
                } else if (activity is TaskListActivity) {
                    activity.boardMembersDetailList(usersList)
                }
                Utils.countingIdlingResource.decrement()
            }
            .addOnFailureListener { e ->
                if (activity is MembersActivity) {
                    activity.hideProgressDialog()
                } else if (activity is TaskListActivity) {
                    activity.hideProgressDialog()
                }
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while creating a board.",
                    e
                )
                Utils.countingIdlingResource.decrement()
            }
    }

    fun getMemberDetails(activity: MembersActivity, email: String) {
        Utils.countingIdlingResource.increment()
        mFireStore.collection(Constants.USERS)
            .whereEqualTo(Constants.EMAIL, email)
            .get()
            .addOnSuccessListener { document ->
                if (document.documents.size > 0) {
                    val user = document.documents[0].toObject(User::class.java)!!
                    activity.memberDetails(user)
                } else {
                    activity.hideProgressDialog()
                    activity.showErrorSnackBar("No such member found.")
                }
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while getting user details",
                    e
                )
                Utils.countingIdlingResource.decrement()
            }
    }

    fun assignMemberToBoard(activity: MembersActivity, board: Board, user: User) {
        Utils.countingIdlingResource.increment()
        val assignedToHashMap = HashMap<String, Any>()
        assignedToHashMap[Constants.ASSIGNED_TO] = board.assignedTo
        mFireStore.collection(Constants.BOARDS)
            .document(board.documentID)
            .update(assignedToHashMap)
            .addOnSuccessListener {
                activity.memberAssignSuccess(user)
                Utils.countingIdlingResource.decrement()
            }.addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error while creating a bord.",
                    e
                )
                Utils.countingIdlingResource.decrement()
            }
    }

    fun getCurrentUserId(): String {
        Utils.countingIdlingResource.increment()
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        Utils.countingIdlingResource.decrement()
        return currentUserID
    }
}
