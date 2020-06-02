package com.example.projemanag.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthUser(
    @Expose
    @SerializedName("localId")
    val localId: String = "",

    @Expose
    @SerializedName("email")
    val email: String = "",

    @Expose
    @SerializedName("displayName")
    val displayName: String = "",

    @Expose
    @SerializedName("idToken")
    val idToken: String = "",

    @Expose
    @SerializedName("registered")
    val registered: Boolean = false,

    @Expose
    @SerializedName("refreshToken")
    val refreshToken: String = "",

    @Expose
    @SerializedName("expiresIn")
    val expiresIn: String = ""
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readBoolean(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(localId)
        parcel.writeString(email)
        parcel.writeString(displayName)
        parcel.writeString(idToken)
        parcel.writeBoolean(registered)
        parcel.writeString(refreshToken)
        parcel.writeString(expiresIn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AuthUser> {
        override fun createFromParcel(parcel: Parcel): AuthUser {
            return AuthUser(parcel)
        }

        override fun newArray(size: Int): Array<AuthUser?> {
            return arrayOfNulls(size)
        }
    }


}