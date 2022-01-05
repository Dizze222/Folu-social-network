package ch.b.retrofitandcoroutines.core

abstract class Abstract {
    abstract class Object<T,M : Mapper>{
      abstract fun map(mapper: M) : T
    }


    interface Mapable<T,M : Mapper> {
       fun map(mapper: M) : T
    }


    interface Mapper{
        class Empty : Mapper
    }



}