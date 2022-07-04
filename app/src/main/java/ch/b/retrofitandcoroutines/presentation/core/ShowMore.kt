package ch.b.retrofitandcoroutines.presentation.core

interface ShowMore<T,E> : Show<T> {

    fun show(arg: T, arg2: E) = Unit
}