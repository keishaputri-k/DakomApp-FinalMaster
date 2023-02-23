package com.kei.dakomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class LectureItem(

    @SerializedName("date")
    val date: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("orginizer_name")
    val orginizerName: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("lecturer")
    val lecturer: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("poster_photo_path")
    val posterPhotoPath: String,

    @SerializedName("cp")
    val cp: String,

    @SerializedName("form_link")
    val formLink: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("quota")
    val quota: Int,

    @SerializedName("group_link")
    val groupLink: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("location")
    val location: String,

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("time")
    val time: String,

    @SerializedName("category")
    val category: String
): Parcelable
