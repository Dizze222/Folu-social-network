package ch.b.retrofitandcoroutines.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.b.retrofitandcoroutines.data.repository.MainDataSource
import ch.b.retrofitandcoroutines.data.repository.MainRepository
import ch.b.retrofitandcoroutines.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mainDataSource: MainDataSource) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(mainDataSource)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}