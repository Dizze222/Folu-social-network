package ch.b.retrofitandcoroutines.user_profile.domain

import ch.b.retrofitandcoroutines.user_profile.data.UserProfileRepository
import ch.b.retrofitandcoroutines.user_profile.data.mapper.UserProfileListDataToDomainMapper

interface UserProfileInteractor {

    suspend fun sendPhoto(photo: String)

    suspend fun getProfileInfo(): UserProfileListDomain


    class Base(
        private val repository: UserProfileRepository,
        private val mapper: UserProfileListDataToDomainMapper
    ) : UserProfileInteractor {

        override suspend fun sendPhoto(photo: String) {
            repository.sendPhoto(photo)
        }

        override suspend fun getProfileInfo(): UserProfileListDomain {
            return repository.getProfileInfo().map(mapper)
        }
    }


}