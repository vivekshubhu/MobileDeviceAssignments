package com.miu.mdp.data

import android.os.Parcel
import android.os.Parcelable

data class Education(
    var imageUrl: String?,
    var collegeTitle: String?,
    var degreeTitle: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(collegeTitle)
        parcel.writeString(degreeTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Education> {
        override fun createFromParcel(parcel: Parcel): Education {
            return Education(parcel)
        }

        override fun newArray(size: Int): Array<Education?> {
            return arrayOfNulls(size)
        }
    }
}
