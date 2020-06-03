package com.example.projemanag.repository

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.example.projemanag.activities.CardDetailsActivity
import com.example.projemanag.activities.CreateBoardActivity
import com.example.projemanag.activities.MainActivity
import com.example.projemanag.activities.MembersActivity
import com.example.projemanag.activities.MyProfileActivity
import com.example.projemanag.activities.SignInActivity
import com.example.projemanag.activities.SignUpActivity
import com.example.projemanag.activities.TaskListActivity
import com.example.projemanag.api.RetrofitBuilder
import com.example.projemanag.api.UserData
import com.example.projemanag.models.Board
import com.example.projemanag.models.Data.authUser
import com.example.projemanag.models.Data.currentUser
import com.example.projemanag.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.HttpRetryException

class Repository {

    fun addRegisteredUser(activity: SignUpActivity, user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                RetrofitBuilder.apiService.createUser(
                    getCurrentUserId(),
                    UserData(
                        user.email,
                        user.id,
                        user.image,
                        user.mobile.toString(),
                        user.name
                    )
                )
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        println("Response $response")
                        currentUser = user
                        loadUserData(activity)
                        activity.userRegisteredSuccess()
                        Log.d(TAG, "signInWithEmail:success")
                    } else {
                        Log.e(activity.javaClass.simpleName, "Error ")
                    }
                } catch (e: Exception) {
                    Log.e(activity.javaClass.simpleName, "Error ", e)
                }
            }
        }
    }

    fun getBoardDetails(activity: TaskListActivity, documentId: String) {
        activity.hideProgressDialog()
        //TODO implement
        Log.d("TODO", "get board details")
    }

    fun deleteBoard(activity: TaskListActivity, board: Board) {
        activity.hideProgressDialog()
        //TODO implement
        Log.d("TODO", "delete board")
    }

    fun createBoard(activity: CreateBoardActivity, board: Board) {
        activity.hideProgressDialog()
        //TODO implement
        Log.d("TODO", "create board")
    }

    fun addUpdateTaskList(activity: Activity, board: Board) {
        if (activity is TaskListActivity) {
            activity.hideProgressDialog()
        } else if (activity is CardDetailsActivity) {
            activity.hideProgressDialog()
        }
        //TODO implement
        Log.d("TODO", "addUpdateTaskList")
    }

    fun updateUserProfileData(activity: MyProfileActivity, userHashMap: HashMap<String, Any>) {
        activity.hideProgressDialog()
        //TODO implement
        Log.d("TODO", "updateUserProfileData")
    }

    fun getAssignedMembersListDetails(activity: Activity, assignedTo: ArrayList<String>) {
        if (activity is MembersActivity) {
            activity.hideProgressDialog()
        } else if (activity is TaskListActivity) {
            activity.hideProgressDialog()
        }
        //TODO implement
        Log.d("TODO", "getAssignedMembersListDetails")
    }

    fun getMemberDetails(activity: MembersActivity, email: String) {
        activity.hideProgressDialog()
        //TODO implement
        Log.d("TODO", "getMemberDetails")
    }

    fun assignMemberToBoard(activity: MembersActivity, board: Board, user: User) {
        activity.hideProgressDialog()
        //TODO implement
        Log.d("TODO", "assignMemberToBoard")
    }

    fun getBoardsList(activity: MainActivity) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitBuilder.apiService.getBoards()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val list = response.body()
                        if (list != null) {
                            /* TODO send ArrayList of Baords from response*/
//                            boardsList = list
//                            activity.populateBoardsListToUI(boardsList)
                        } else {
                            println("List is empty")
                            activity.populateBoardsListToUI(ArrayList<Board>())
                        }
                    } else {
                        println("Error: ${response.code()}")
                    }
                } catch (e: HttpRetryException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }

    fun loadUserData(activity: Activity, readBoardsList: Boolean = false) {
        println("Loading user data")
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                RetrofitBuilder.apiService.getUser(getCurrentUserId())
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        println("Response $response")
                        currentUser = response.body()!!
                        when (activity) {
                            is SignInActivity -> {
                                activity.signInSuccess()
                            }
                            is MainActivity -> {
                                activity.updateNavigationUserDetails(currentUser, readBoardsList)
                            }
                            is MyProfileActivity -> {
                                activity.setUserDataInUI(currentUser)
                            }
                        }
                        Log.d(TAG, "signInWithEmail:success")
                    } else {
                        when (activity) {
                            is SignInActivity -> {
                                activity.hideProgressDialog()
                            }
                            is MainActivity -> {
                                activity.hideProgressDialog()
                            }
                        }
                        Log.e(activity.javaClass.simpleName, "Error ")
                    }
                } catch (e: Exception) {
                    Log.e(activity.javaClass.simpleName, "Error ", e)
                }
            }
        }
    }

    fun getCurrentUserId(): String {
        return authUser.localId
    }
}