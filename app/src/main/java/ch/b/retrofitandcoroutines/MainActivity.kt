package ch.b.retrofitandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import ch.b.retrofitandcoroutines.databinding.ActivityMainBinding
import ch.b.retrofitandcoroutines.presentation.screens.PhotographersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,PhotographersFragment()).commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar,menu)
        return true
    }
}