package ch.b.retrofitandcoroutines.data.certain_post


import android.util.Log
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerListData
import ch.b.retrofitandcoroutines.data.all_posts.mappers.ExceptionPostsMapper
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource
import ch.b.retrofitandcoroutines.data.core.ExceptionAuthMapper

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
            Log.i("EXP",e.toString())
            val messageError = exceptionMapper.mapper(e)
            PhotographerListData.Fail(messageError)
        }

    }
}