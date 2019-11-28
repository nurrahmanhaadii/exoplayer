package id.haadii.learn.exoplayer.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Videos(
    val medium: Data
) : Parcelable