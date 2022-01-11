package ch.b.retrofitandcoroutines.domain


import ch.b.retrofitandcoroutines.data.PhotographersDataToDomainMapper
import ch.b.retrofitandcoroutines.data.PhotographersRepository

interface PhotographersInteractor {
    suspend fun getPhotographers() : PhotographersDomain
    class Base(private val repository: PhotographersRepository,
               private val mapper: PhotographersDataToDomainMapper) : PhotographersInteractor{
        override suspend fun getPhotographers() = repository.getPhotographers().map(mapper)
    }
}