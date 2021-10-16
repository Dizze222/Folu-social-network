package ch.b.retrofitandcoroutines.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDTO(
    @SerializedName("id")
    val ID: String,
    @SerializedName("author")
    val authorOfPicture: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("url")
    val URL: String,
    @SerializedName("download_url")
    val downloadedPicture: String
)

