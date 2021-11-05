package ch.b.retrofitandcoroutines.utils
import ch.b.retrofitandcoroutines.utils.Status.ERROR
import ch.b.retrofitandcoroutines.utils.Status.LOADING
import ch.b.retrofitandcoroutines.utils.Status.SUCCESS


/*
class Resource<T>(val status: Status,val data:T,val message: String?) {

    companion object {
        fun<T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun<T> error(data: T?,message: String) : Resource<T?> = Resource(status = Status.ERROR, data = data, message = message)

        fun<T> loading(data: T?) : Resource<T?> = Resource(status = Status.LOADING, data = data, message = null)
    }
} */
sealed class Resource<out T>(val status: Status,val data: T?,val message: String?){
    class Success<T>(data: T?) : Resource<T>(status = SUCCESS,data = data,message = null)
    class Error<T>(data: T?,message: String?) : Resource<T>
        (status = Status.ERROR,data = data,message = message)
    class Loading<T>(data: T?) : Resource<T>(status = ERROR,data = data,message = null)
}



enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}