package ch.b.retrofitandcoroutines.all_posts.domain


import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerRepository
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerCloud
import retrofit2.Response

interface PhotographerInteractor {

    suspend fun readDataFromCloud(): PhotographerListDomain

    suspend fun searchPhotographers(author: String) : PhotographerListDomain

    suspend fun like(
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
        override suspend fun readDataFromCloud(): PhotographerListDomain {
            return repository.getAllPhotographers().map(mapper)
        }

        override suspend fun searchPhotographers(author: String): PhotographerListDomain {
            return repository.searchPhotographers(author).map(mapper)
        }

        override suspend fun like(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ) = repository.like(author, idPhotographer, like, theme, url)
    }
}