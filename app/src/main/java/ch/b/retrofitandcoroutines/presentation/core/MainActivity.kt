
package ch.b.retrofitandcoroutines.presentation.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts

import ch.b.retrofitandcoroutines.*
import ch.b.retrofitandcoroutines.databinding.ActivityMainBinding
import ch.b.retrofitandcoroutines.presentation.containers.*
import ch.b.retrofitandcoroutines.presentation.navigate.*

import ru.terrakok.cicerone.Cicerone


class MainActivity : AppCompatActivity(), ResultApiActivity,
    RouterProvider {
    private lateinit var binding: ActivityMainBinding
    private val cicerone = Cicerone.create(AppRouter())
    private lateinit var appNavigator: AppNavigator

    override val router: AppRouter
        get() = cicerone.router

    private val image = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        val fragment = supportFragmentManager.fragments.first() as ImageResult
        uri?.let {
            fragment.onImageResult(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appNavigator = AppNavigator(this, R.id.container)
        appNavigator.initContainers()
        val screen = FragmentScreen(SplashContainer.newInstance())
        router.replaceTab(screen)
        binding.navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_posts -> {
                    val allPostsScreen =
                        FragmentScreen(AllPostTabContainer.newInstance())
                    router.replaceTab(allPostsScreen)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_likes -> {
                    val likesScreen =
                        FragmentScreen(LikedTabContainer.newInstance())
                    router.replaceTab(likesScreen)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_search ->{
                    val searchScreen = FragmentScreen(SearchTabContainer.newInstance())
                    router.replaceTab(searchScreen)
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile ->{
                    val profileScreen = FragmentScreen(UserProfileContainer.newInstance())
                    router.replaceTab(profileScreen)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        if (savedInstanceState == null) {
            val splashScreen = FragmentScreen(SplashContainer.newInstance())
            router.replaceTab(splashScreen)
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if ((fragment != null && fragment is BackButtonListener && fragment.onBackPressed())) {
            return
        } else {
            router.exit()
        }
    }

    override fun onPause() {
        super.onPause()
        cicerone.navigatorHolder.removeNavigator()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        cicerone.navigatorHolder.setNavigator(appNavigator)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.navigation_icon, menu)
        return true
    }

    override fun image() = image.launch("image/*")
}