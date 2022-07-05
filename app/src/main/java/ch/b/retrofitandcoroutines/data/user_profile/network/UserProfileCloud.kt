package ch.b.retrofitandcoroutines.data.user_profile.network

import ch.b.retrofitandcoroutines.core.Abstract

interface UserProfileCloud {

    fun <T> map(mapper: Abstract.ToProfileMapper<T>): T

    /*data*/class Base(
        private val name: String,
        private val secondName: String,
        private val dio: String,
        private val image: String
    ) : UserProfileCloud{
        override fun <T> map(mapper: Abstract.ToProfileMapper<T>): T =
            mapper.map(name, secondName, dio, image)
    }



}