package ch.b.retrofitandcoroutines.domain.all_posts


import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerRepository
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud
import retrofit2.Response

interface PhotographerInteractor {

    suspend fun readDataFromCloud(): PhotographerListDomain

    suspend fun searchPhotographers(author: String) : PhotographerListDomain

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
        override suspend fun readDataFromCloud(): PhotographerListDomain {
            return repository.getAllPhotographers().map(mapper)
        }

        override suspend fun searchPhotographers(author: String): PhotographerListDomain {
            return repository.searchPhotographers(author).map(mapper)
        }

        override suspend fun post(
            author: String,
            idPhotographer: Int,
            like: Int,
            theme: String,
            url: String
        ) = repository.post(author, idPhotographer, like, theme, url)
    }
}