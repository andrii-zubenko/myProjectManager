package com.example.projemanag.api

import com.example.projemanag.models.AuthUser
import com.example.projemanag.models.Board
import com.example.projemanag.models.BoardsList
import com.example.projemanag.models.User
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("./accounts:signInWithPassword")
    suspend fun sighIn(
        @Query("key") key: String,
        @Body loginPostData: LoginPostData
    ): Response<AuthUser>

    @POST("./accounts:signUp")
    suspend fun sighUp(
        @Query("key") key: String,
        @Body oginPostData: LoginPostData
    ): Response<AuthUser>

    @GET("databases/(default)/documents/users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): Response<User>

    @GET("databases/(default)/documents/boards/")
    suspend fun getBoards(
    ): Response<BoardsList>
}

data class LoginPostData(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("returnSecureToken") var returnSecureToken: Boolean = true
)
