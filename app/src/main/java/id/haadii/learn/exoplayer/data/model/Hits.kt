package id.haadii.learn.exoplayer.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hits (
    val id: Int,
    val picture_id: String,
    val videos: Videos,
    val tags: String,
    val downloads: Int,
    val likes: Int,
    val duration: Int,
    val views: Int,
    val userImageURL: String,
    val user: String
) : Parcelable