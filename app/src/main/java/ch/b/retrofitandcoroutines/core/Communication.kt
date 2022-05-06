package ch.b.retrofitandcoroutines.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

interface Communication<T> : Abstract.Mapper.Data<T, Unit> {
    suspend fun observe(owner: LifecycleOwner, observer: FlowCollector<Int>)

    abstract class Base<T> : Communication<T> {
        private val liveData = MutableStateFlow(1)
        override fun map(data: T) {
            liveData.value = data.toString().toInt()
        }

        override suspend fun observe(owner: LifecycleOwner, observer: FlowCollector<Int>) =
            liveData.collect(observer)
    }
}