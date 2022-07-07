package ch.b.retrofitandcoroutines.data.user_profile

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.user_profile.mapper.ExceptionProfileMapper
import ch.b.retrofitandcoroutines.data.user_profile.network.UserProfileCloudDataSource

interface UserProfileRepository {

    suspend fun sendPhoto(photo: String)

    suspend fun getProfileInfo(): UserProfileListData
    class Base(
        private val dataSource: UserProfileCloudDataSource,
        private val mapper: Abstract.ToProfileMapper<UserProfileData>,
        private val exceptionMapper: ExceptionProfileMapper.Base
    ) : UserProfileRepository {
        override suspend fun sendPhoto(photo: String) {
            dataSource.sendPhoto(photo)
        }

        override suspend fun getProfileInfo(): UserProfileListData = try {
            val listOfCloud = dataSource.getProfileInfo()
            UserProfileListData.Success(listOfCloud.map {
                it.map(mapper)
            })
        } catch (e: Exception) {
            val errorMessage = exceptionMapper.map(e)
            UserProfileListData.Fail(errorMessage)
        }
    }
}