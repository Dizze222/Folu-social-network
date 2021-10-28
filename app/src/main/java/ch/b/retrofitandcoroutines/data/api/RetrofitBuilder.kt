package ch.b.retrofitandcoroutines.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 object RetrofitBuilder {
    private const val BASE_URL = "https://picsum.photos/v2/"

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)


/* private val getRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

 */
}