package ch.b.retrofitandcoroutines.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.api.ApiHelper
import ch.b.retrofitandcoroutines.data.api.RetrofitBuilder
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.databinding.FragmentUserBinding
import ch.b.retrofitandcoroutines.ui.base.ViewModelFactory
import ch.b.retrofitandcoroutines.ui.main.adapter.MainAdapter
import ch.b.retrofitandcoroutines.ui.main.viewmodel.MainViewModel
import ch.b.retrofitandcoroutines.utils.Status

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: FragmentUserBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        setupViewModel()
        setupObservers()
        setupUI()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)

    }
    private fun setupObservers(){
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let {resources ->
               when(resources.status){
                   Status.SUCCESS -> {
                       binding.recyclerView.visibility = View.VISIBLE
                       binding.progressBar.visibility = View.GONE
                       resources.data?.let {users ->
                           retrieveList(users)
                       }
                   }
                   Status.LOADING ->{
                       binding.recyclerView.visibility = View.GONE
                       binding.progressBar.visibility = View.VISIBLE
                   }
                   Status.ERROR ->{
                       binding.recyclerView.visibility = View.VISIBLE
                       binding.progressBar.visibility = View.GONE
                   }

               }
            }


        })
    }

    private fun retrieveList(usersDTO: List<UserDTO>){
        adapter.apply {
            addUsers(usersDTO)
            notifyDataSetChanged()
        }
    }

    private fun setupUI(){
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context,(binding.recyclerView.layoutManager as LinearLayoutManager).orientation))
        binding.recyclerView.adapter = adapter
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

}


