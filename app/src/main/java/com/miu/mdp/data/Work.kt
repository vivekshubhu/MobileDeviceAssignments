package com.miu.mdp.data

import android.os.Parcel
import android.os.Parcelable

data class Work(
    var imageUrl: String?,
    var positionTitle: String?,
    var companyName: String?,
    var timeFrame: String?,
    var address: String?,
    var workDescription: String?,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(positionTitle)
        parcel.writeString(companyName)
        parcel.writeString(timeFrame)
        parcel.writeString(address)
        parcel.writeString(workDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Work> {
        override fun createFromParcel(parcel: Parcel): Work {
            return Work(parcel)
        }

        override fun newArray(size: Int): Array<Work?> {
            return arrayOfNulls(size)
        }
    }
}
