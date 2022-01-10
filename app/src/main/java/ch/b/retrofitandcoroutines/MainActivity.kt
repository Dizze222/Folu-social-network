package ch.b.retrofitandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.ActivityMainBinding

import ch.b.retrofitandcoroutines.presentation.PhotographerAdapter
import ch.b.retrofitandcoroutines.presentation.screens.PhotographersFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,PhotographersFragment()).commit()
        val toolbar by lazy{
            supportActionBar
        }
    }
}