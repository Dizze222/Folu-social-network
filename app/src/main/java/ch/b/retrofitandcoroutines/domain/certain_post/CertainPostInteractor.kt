package ch.b.retrofitandcoroutines.domain.certain_post

import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerListDataToDomainMapper
import ch.b.retrofitandcoroutines.data.certain_post.CertainPostRepository
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomain

interface CertainPostInteractor {

    suspend fun getCertainPost(postId: Int): PhotographerListDomain

    class Base(
        private val repository: CertainPostRepository,
        private val mapper: PhotographerListDataToDomainMapper
    ) : CertainPostInteractor {
        override suspend fun getCertainPost(postId: Int): PhotographerListDomain {
            return repository.getCertainPost(postId).map(mapper)
        }

    }

}