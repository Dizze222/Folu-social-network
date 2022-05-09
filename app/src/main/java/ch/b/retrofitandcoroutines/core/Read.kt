package ch.b.retrofitandcoroutines.core

interface Read<T> {
    fun read(): T

    class Base : Read<Int>{
        override fun read(): Int = 0
    }

}