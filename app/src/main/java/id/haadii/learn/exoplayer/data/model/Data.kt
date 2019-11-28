package id.haadii.learn.exoplayer.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val url: String,
    val width: Int,
    val size: Int,
    val height: Int
) : Parcelable