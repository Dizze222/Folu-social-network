package ch.b.retrofitandcoroutines.utils.core


interface Mapper<S, R> {

    fun map(src: S): R

    interface Unit<S> : Mapper<S, kotlin.Unit>
}