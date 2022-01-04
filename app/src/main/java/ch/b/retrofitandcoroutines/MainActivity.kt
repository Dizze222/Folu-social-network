package ch.b.retrofitandcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.ActivityMainBinding
import ch.b.retrofitandcoroutines.presentation.PhotographerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = (application as PhotographerApp).mainViewModel
        val adapter = PhotographerAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.observe(this,{
            adapter.update(it)
        })
        viewModel.getPhotographers()
    }
}