package com.example.projemanag.repository

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.example.projemanag.activities.MainActivity
import com.example.projemanag.activities.MyProfileActivity
import com.example.projemanag.activities.SignInActivity
import com.example.projemanag.api.RetrofitBuilder
import com.example.projemanag.models.Data.authUser
import com.example.projemanag.models.Data.boardsList
import com.example.projemanag.models.Data.currentUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.HttpRetryException

object Repository {

    fun getBoardsList(activity: MainActivity) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitBuilder.apiService.getBoards()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val list = response.body()?.list
                        if(list!=null){
                            boardsList = list
                            activity.populateBoardsListToUI(boardsList)
                        }
                        else{
                            println("List is empty")
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