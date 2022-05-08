package ch.b.retrofitandcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.databinding.ActivityMainBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.navigate.MainViewModel
import ch.b.retrofitandcoroutines.presentation.navigate.Screens.Companion.ALL_PHOTOGRAPHERS
import ch.b.retrofitandcoroutines.presentation.navigate.Screens.Companion.CERTAIN_POST
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launchWhenStarted {
            viewModel.observe(this@MainActivity, {
                val fragment = when (it) {
                    ALL_PHOTOGRAPHERS -> PhotographersFragment()
                    CERTAIN_POST -> PhotographerDetailFragment()
                    else -> throw IllegalStateException("screen id undefined $it")
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            })
        }
        viewModel.init()

    }

    override fun onBackPressed() {
        if (viewModel.navigateBack())
            super.onBackPressed()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar, menu)
        return true
    }
}