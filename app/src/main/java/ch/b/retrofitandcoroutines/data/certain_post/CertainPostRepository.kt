package ch.b.retrofitandcoroutines.data.certain_post

import ch.b.retrofitandcoroutines.all_posts.data.PhotographerListData
import ch.b.retrofitandcoroutines.all_posts.data.mappers.ExceptionPostsMapper
import ch.b.retrofitandcoroutines.all_posts.data.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource

interface CertainPostRepository {
    suspend fun getCertainPost(postId: Int): PhotographerListData

    class Base(
        private val cloudDataSource: CertainPostDataSource,
        private val cloudMapper: PhotographerListCloudMapper,
        private val exceptionMapper: ExceptionPostsMapper
    ) : CertainPostRepository {
        override suspend fun getCertainPost(postId: Int): PhotographerListData = try {
            val certainPostList = cloudDataSource.getCertainPost(postId)
            PhotographerListData.Success(cloudMapper.map(certainPostList))
        } catch (e: Exception) {
            val messageError = exceptionMapper.mapper(e)
            PhotographerListData.Fail(messageError)
        }

    }
}