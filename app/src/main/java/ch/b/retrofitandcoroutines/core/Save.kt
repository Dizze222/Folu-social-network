package ch.b.retrofitandcoroutines.core

interface Save<T> {
    fun save(data: T)
}