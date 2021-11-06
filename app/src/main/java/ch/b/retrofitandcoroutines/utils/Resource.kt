package ch.b.retrofitandcoroutines.utils
import ch.b.retrofitandcoroutines.utils.Status.LOADING
import ch.b.retrofitandcoroutines.utils.Status.SUCCESS

sealed class Resource<T>(val status: Status,val data: T?,val message: String?){
    class Success<T>(data: T?) : Resource<T>(status = SUCCESS,data = data,message = null)
    class Error<T>(data: T?,message: String?) : Resource<T>
        (status = Status.ERROR,data = data,message = message)
    class Loading<T>(data: T?) : Resource<T>(status = LOADING,data = data,message = null)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}