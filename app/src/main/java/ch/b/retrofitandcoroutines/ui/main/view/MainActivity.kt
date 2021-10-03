package ch.b.retrofitandcoroutines.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.databinding.ActivityMainBinding
import ch.b.retrofitandcoroutines.ui.main.adapter.MainAdapter
import ch.b.retrofitandcoroutines.ui.main.viewmodel.MainViewModel
import ch.b.retrofitandcoroutines.ui.screens.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment()).commit()
        setContentView(binding.root)


    }

   /* private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users ->
                            retrieveList(users)
                        }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(userDTOS: List<UserDTO>) {
        adapter.apply {
            addUsers(userDTOS)
            notifyDataSetChanged()
        }
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context, (binding.recyclerView.layoutManager as LinearLayoutManager).orientation))
        binding.recyclerView.adapter = adapter
    }
*/
}
