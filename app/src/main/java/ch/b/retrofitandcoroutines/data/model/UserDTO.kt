package ch.b.retrofitandcoroutines.data.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val ID: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("download_url")
    val downloadedPicture: String
)

