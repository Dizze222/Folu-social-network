package ch.b.retrofitandcoroutines.domain.user_profile

import ch.b.retrofitandcoroutines.data.user_profile.UserProfileRepository
import ch.b.retrofitandcoroutines.data.user_profile.mapper.UserProfileListDataToDomainMapper

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