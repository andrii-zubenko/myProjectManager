package com.example.projemanag.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Board(
    @Expose
    @SerializedName("name")
    val name: String = "",

    @Expose
    @SerializedName("image")
    val image: String = "",

    @Expose
    @SerializedName("createdBy")
    val createdBy: String = "",

    @Expose
    @SerializedName("assignedTo")
    val assignedTo: ArrayList<String> = ArrayList(),

    @Expose
    @SerializedName("documentID")
    var documentID: String = "",

    @Expose
    @SerializedName("taskList")
    var taskList: ArrayList<Task> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Task.CREATOR)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(createdBy)
        parcel.writeStringList(assignedTo)
        parcel.writeString(documentID)
        parcel.writeTypedList(taskList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Board> {
        override fun createFromParcel(parcel: Parcel): Board {
            return Board(parcel)
        }

        override fun newArray(size: Int): Array<Board?> {
            return arrayOfNulls(size)
        }
    }
}
