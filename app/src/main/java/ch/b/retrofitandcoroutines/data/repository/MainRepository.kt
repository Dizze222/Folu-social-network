package ch.b.retrofitandcoroutines.data.repository


class MainRepository(private val dataSource: MainDataSource) {

  suspend  fun getUsers()  = dataSource.getUsers()

}



