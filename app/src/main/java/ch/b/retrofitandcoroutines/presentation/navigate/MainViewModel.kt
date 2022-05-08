package ch.b.retrofitandcoroutines.presentation.navigate

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import ch.b.retrofitandcoroutines.core.NavigationCommunication
import ch.b.retrofitandcoroutines.core.Read
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Read<Int>,
    private val communication: NavigationCommunication
) : ViewModel() {
    fun init() {
        communication.map(navigator.read())
    }

    suspend fun observe(owner: LifecycleOwner, observer: FlowCollector<Int>) {
        communication.observe(owner, observer)
    }

    fun navigateBack(): Boolean {
        val currentScreen = navigator.read()
        val exit = currentScreen == 0
        if (!exit) {
            val newScreen = currentScreen - 1
            communication.map(newScreen)
        }
        return exit
    }

}