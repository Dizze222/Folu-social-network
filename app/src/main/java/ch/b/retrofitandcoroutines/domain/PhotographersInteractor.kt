package ch.b.retrofitandcoroutines.domain


import ch.b.retrofitandcoroutines.data.mappers.PhotographersDataToDomainMapper
import ch.b.retrofitandcoroutines.data.PhotographersRepository
import ch.b.retrofitandcoroutines.data.net.PhotographerCloud
import retrofit2.Response

interface PhotographersInteractor {
    suspend fun getPhotographers() : PhotographersDomain
    suspend fun post(author: String,idPhotographer: Int,like: Int,theme: String,url: String) : Response<PhotographerCloud>
    class Base(private val repository: PhotographersRepository,
               private val mapper: PhotographersDataToDomainMapper
    ) : PhotographersInteractor{
        override suspend fun getPhotographers() : PhotographersDomain{
            return repository.getPhotographers().map(mapper)
        }

        override suspend fun post(author: String,idPhotographer: Int,like: Int,theme: String,url: String): Response<PhotographerCloud> {
           return repository.post(author, idPhotographer, like, theme, url)
        }
    }
}