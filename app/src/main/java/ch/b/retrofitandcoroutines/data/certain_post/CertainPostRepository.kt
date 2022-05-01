package ch.b.retrofitandcoroutines.data.certain_post

import ch.b.retrofitandcoroutines.data.all_posts.PhotographerListData
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerCloud
import ch.b.retrofitandcoroutines.data.all_posts.net.PhotographerListCloudMapper
import ch.b.retrofitandcoroutines.data.certain_post.net.CertainPostDataSource

interface CertainPostRepository {
    suspend fun getCertainPost(postId: Int) : PhotographerListData

    class Base(private val cloudDataSource: CertainPostDataSource,
               private val cloudMapper: PhotographerListCloudMapper
               ) : CertainPostRepository{
        override suspend fun getCertainPost(postId: Int): PhotographerListData = try{
            val certainPostList = cloudDataSource.getCertainPost(postId)
            PhotographerListData.Success(cloudMapper.map(certainPostList))
        }catch (e: Exception){
            PhotographerListData.Fail(e)
        }

    }
}