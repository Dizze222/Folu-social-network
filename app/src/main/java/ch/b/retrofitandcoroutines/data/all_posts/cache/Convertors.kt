package ch.b.retrofitandcoroutines.data.all_posts.cache

import androidx.lifecycle.LiveData
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Convertors{

    @TypeConverter
    fun fromAuthorOfComments(authors: List<String>) : String{
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().toJson(authors, type)
    }

    @TypeConverter
    fun toAuthorOfComments(json: String) : List<String>{
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, type)
    }
}