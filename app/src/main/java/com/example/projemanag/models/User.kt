package com.example.projemanag.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("id")
    val id: String = "",

    @Expose
    @SerializedName("name")
    val name: String = "",

    @Expose
    @SerializedName("email")
    val email: String = "",

    @Expose
    @SerializedName("image")
    val image: String = "",

    @Expose
    @SerializedName("mobile")
    val mobile: Long = 0,

    @Expose
    @SerializedName("fcmToken")
    val fcmToken: String = "",

    @Expose
    @SerializedName("selected")
    var selected: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readBoolean()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(image)
        parcel.writeLong(mobile)
        parcel.writeString(fcmToken)
        parcel.writeBoolean(selected)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "id:$id name:$name email:$email"
    }
}
