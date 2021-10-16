package ch.b.retrofitandcoroutines.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.api.ApiHelper
import ch.b.retrofitandcoroutines.data.api.RetrofitBuilder
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.databinding.FragmentUserBinding
import ch.b.retrofitandcoroutines.ui.base.ViewModelFactory
import ch.b.retrofitandcoroutines.ui.main.adapter.AdapterOnClick
import ch.b.retrofitandcoroutines.ui.main.adapter.MainAdapter
import ch.b.retrofitandcoroutines.ui.main.viewmodel.MainViewModel
import ch.b.retrofitandcoroutines.utils.Status

class MainFragment : Fragment(), AdapterOnClick {


    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)
    }
    private lateinit var adapter: MainAdapter
    private lateinit var binding: FragmentUserBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        setupObservers()
        setupUI()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let { resources ->
                when (resources.status) {
                    Status.SUCCESS -> {
                        binding.shimmerFrameLayout.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        resources.data?.let { users ->
                            retrieveList(users)
                        }
                    }
                    Status.LOADING -> {
                        binding.recyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(usersDTO: List<UserDTO>) {
        adapter.apply {
            addUsers(usersDTO)
        }
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
        adapter = MainAdapter(arrayListOf(), this)
        binding.recyclerView.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    @SuppressLint("ShowToast")
    override fun onClick(item: UserDTO) {
        Toast.makeText(context, item.authorOfPicture, Toast.LENGTH_LONG).show()
    }

}


