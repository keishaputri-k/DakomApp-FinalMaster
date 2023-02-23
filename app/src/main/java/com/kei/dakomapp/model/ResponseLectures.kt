package com.kei.dakomapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseLectures (

	@field:SerializedName("lecture")
	var lecture: List<LectureItem>,

	@field:SerializedName("status")
	val status: String? = null


)




