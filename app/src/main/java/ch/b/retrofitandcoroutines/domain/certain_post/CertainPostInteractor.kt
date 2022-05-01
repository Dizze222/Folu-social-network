package ch.b.retrofitandcoroutines.domain.certain_post

import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomain

interface CertainPostInteractor {

    suspend fun getCertainPost(postId: Int) : PhotographerListDomain

    class Base(private val repository: CertainPostRepository,
                private val mapper: PhotographerListDataToDomainMapper) : CertainPostInteractor{
        override suspend fun getCertainPost(postId: Int) : PhotographerListDomain {
            return repository.getCertainPost(postId).map(mapper)
        }

    }

}