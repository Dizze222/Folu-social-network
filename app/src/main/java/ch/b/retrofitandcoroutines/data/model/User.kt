package ch.b.retrofitandcoroutines.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userEmail: String,
    @SerializedName("width")
    val userName: String,
    @SerializedName("download_url")
    val avatarImage: String
)

