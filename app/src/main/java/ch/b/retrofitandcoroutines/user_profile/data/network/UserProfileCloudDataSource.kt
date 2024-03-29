package ch.b.retrofitandcoroutines.user_profile.data.network


interface UserProfileCloudDataSource {

    suspend fun sendPhoto(photo: String)

    suspend fun getProfileInfo() : List<UserProfileCloud.Base>

    class Base(private val service: UserProfileService) : UserProfileCloudDataSource {
        override suspend fun sendPhoto(photo: String) {
            service.sendPhoto(photo)
        }

        override suspend fun getProfileInfo(): List<UserProfileCloud.Base> {
          return service.getProfileInfo().body()!!
        }

    }


}