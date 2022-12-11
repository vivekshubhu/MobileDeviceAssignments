package com.miu.mdp.data

import android.os.Parcel
import android.os.Parcelable

data class Certification(
    var imageUrl: String?,
    var certificationTitle: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(certificationTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Certification> {
        override fun createFromParcel(parcel: Parcel): Certification {
            return Certification(parcel)
        }

        override fun newArray(size: Int): Array<Certification?> {
            return arrayOfNulls(size)
        }
    }
}
