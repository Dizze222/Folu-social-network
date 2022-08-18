package ch.b.retrofitandcoroutines.user_profile.data

import ch.b.retrofitandcoroutines.utils.core.Abstract
import ch.b.retrofitandcoroutines.user_profile.data.mapper.ExceptionProfileMapper
import ch.b.retrofitandcoroutines.user_profile.data.network.UserProfileCloudDataSource

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