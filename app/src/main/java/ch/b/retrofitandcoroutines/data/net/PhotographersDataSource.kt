package ch.b.retrofitandcoroutines.data.net

interface PhotographersDataSource{
    suspend fun getPhotographers() : List<PhotographerCloud>
    class Base(private val service: PhotographerService) : PhotographersDataSource{
        override suspend fun getPhotographers(): List<PhotographerCloud> {
           return service.getPhotographers()
        }
    }
}