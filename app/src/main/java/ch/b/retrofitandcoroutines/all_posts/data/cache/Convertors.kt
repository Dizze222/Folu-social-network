package ch.b.retrofitandcoroutines.all_posts.data.cache

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

    @TypeConverter
    fun fromIntList(list: List<Int>) : String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toIntList(list: String) : List<Int>{
        return Gson().fromJson(list,object : TypeToken<List<Int>>(){}.type)
    }



}