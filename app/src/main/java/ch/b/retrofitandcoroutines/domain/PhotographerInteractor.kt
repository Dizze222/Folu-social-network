package ch.b.retrofitandcoroutines.domain


import ch.b.retrofitandcoroutines.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.PhotographerRepository
import ch.b.retrofitandcoroutines.data.net.PhotographerCloud
import retrofit2.Response

interface PhotographerInteractor {
    suspend fun getPhotographers(): PhotographerListDomain
    suspend fun post(
        author: String,
        idPhotographer: Int,
        like: Int,
        theme: String,
        url: String
    ): Response<PhotographerCloud>

    class Base(
        private val repository: PhotographerRepository,
        private val mapper: PhotographerListDataToDomainMapper
    ) : PhotographerInteractor {
        override suspend fun getPhotographers() = repository.getPhotographers().map(mapper)


        override suspend fun post(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ) = repository.post(author, idPhotographer, like, theme, url)
    }
}