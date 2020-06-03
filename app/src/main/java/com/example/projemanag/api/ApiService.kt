package com.example.projemanag.api

import com.example.projemanag.models.AuthUser
import com.example.projemanag.models.User
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("./accounts:signInWithPassword")
    suspend fun signIn(
        @Query("key") key: String,
        @Body loginPostData: LoginPostData
    ): Response<AuthUser>

    @POST("./accounts:signUp")
    suspend fun signUp(
        @Query("key") key: String,
        @Body loginPostData: LoginPostData
    ): Response<AuthUser>

    @GET("users/{id}.json")
    suspend fun getUser(
        @Path("id") id: String
    ): Response<User>

    @PUT("users/{id}.json")
    suspend fun createUser(
        @Path("id") id: String,
        @Body userData: UserData
    ): Response<User>

    @GET("boards.json")
    suspend fun getBoards(
    ): Response<JSONObject> /* TODO fix response, parse JSON in ArrayList*/
}

data class LoginPostData(
    @SerializedName("email") var loginEmail: String,
    @SerializedName("password") var loginPassword: String,
    @SerializedName("returnSecureToken") var returnSecureToken: Boolean = true
)

data class UserData(
    @SerializedName("email") var userEmail: String,
    @SerializedName("id") var userId: String,
    @SerializedName("image") var userImage: String = "",
    @SerializedName("mobile") var userMobile: String = "",
    @SerializedName("name") var userName: String = ""
)

