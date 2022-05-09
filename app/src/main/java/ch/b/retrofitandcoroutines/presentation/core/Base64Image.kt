package ch.b.retrofitandcoroutines.presentation.core

interface Base64Image<T> {

    fun base64Image(param: T) : String

}