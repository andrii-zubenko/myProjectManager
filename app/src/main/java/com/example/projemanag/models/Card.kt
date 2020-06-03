package com.example.projemanag.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Card(

    @Expose
    @SerializedName("name")
    val name: String = "",

    @Expose
    @SerializedName("createdBy")
    val createdBy: String = "",

    @Expose
    @SerializedName("assignedTo")
    val assignedTo: ArrayList<String> = ArrayList(),

    @Expose
    @SerializedName("labelColor")
    val labelColor: String = "",

    @Expose
    @SerializedName("dueDate")
    val dueDate: Long = 0
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.createStringArrayList()!!,
        source.readString()!!,
        source.readLong()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(createdBy)
        writeStringList(assignedTo)
        writeString(labelColor)
        writeLong(dueDate)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Card> = object : Parcelable.Creator<Card> {
            override fun createFromParcel(source: Parcel): Card = Card(source)
            override fun newArray(size: Int): Array<Card?> = arrayOfNulls(size)
        }
    }
}
