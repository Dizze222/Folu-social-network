package ch.b.retrofitandcoroutines.presentation.core

interface Launch<T> {

    fun launch(arg: T)

}