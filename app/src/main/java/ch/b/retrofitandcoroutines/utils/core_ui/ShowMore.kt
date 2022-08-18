package ch.b.retrofitandcoroutines.utils.core_ui

interface ShowMore<T,E> : Show<T> {

    fun show(arg: T, arg2: E) = Unit
}