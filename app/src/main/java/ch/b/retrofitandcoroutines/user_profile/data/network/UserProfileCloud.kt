package ch.b.retrofitandcoroutines.user_profile.data.network

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface UserProfileCloud {

    fun <T> map(mapper: Abstract.ToProfileMapper<T>): T

    /*data*/class Base(
        private val name: String,
        private val secondName: String,
        private val bio: String,
        private val image: String
    ) : UserProfileCloud {
        override fun <T> map(mapper: Abstract.ToProfileMapper<T>): T =
            if (bio.isEmpty()) {
                mapper.map(name, secondName, "О себе", image)
            } else {
                mapper.map(name, secondName, bio, image)
            }
    }
}