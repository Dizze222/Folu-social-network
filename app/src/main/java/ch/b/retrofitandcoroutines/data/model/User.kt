package ch.b.retrofitandcoroutines.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatarImage: String,
    @SerializedName("email")
    val userEmail: String,
    @SerializedName("id")
    val userID: String,
    @SerializedName("name")
    val userName: String


)
