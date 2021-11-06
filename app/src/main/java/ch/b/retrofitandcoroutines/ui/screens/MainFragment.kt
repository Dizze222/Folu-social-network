package ch.b.retrofitandcoroutines.ui.screens

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.api.RetrofitBuilder
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.data.repository.MainDataSource
import ch.b.retrofitandcoroutines.databinding.FragmentUserBinding
import ch.b.retrofitandcoroutines.ui.base.ViewModelFactory
import ch.b.retrofitandcoroutines.ui.main.adapter.AdapterOnClick
import ch.b.retrofitandcoroutines.ui.main.adapter.MainAdapter
import ch.b.retrofitandcoroutines.ui.main.viewmodel.MainViewModel
import ch.b.retrofitandcoroutines.utils.Resource
import ch.b.retrofitandcoroutines.utils.Status
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment(), AdapterOnClick {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(MainDataSource(RetrofitBuilder.apiService)))
            .get(MainViewModel::class.java)
    }
    private lateinit var adapter: MainAdapter
    private lateinit var binding: FragmentUserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
        viewModel.getUsers()
        setupObservers()
        setupUI()

    }
   private fun setupObservers() {
        viewModel.newResponse.observe(viewLifecycleOwner, Observer {
                when(it.status){
                    Status.SUCCESS -> {
                        Log.i("TAG","success Fragment")
                        retrieveList(it.data!!)
                        adapter.isShimmer = true
                    }
                    Status.LOADING ->{
                        adapter.isShimmer = true
                        Log.i("TAG","loading Fragment")
                    }
                    Status.ERROR ->{
                        Log.i("TAG","error Fragment")
                    }

                }
        })
    }

    private fun retrieveList(usersDTO: List<UserDTO>) {
        adapter.addUsers(usersDTO)
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

    override fun onClick(item: UserDTO) {
        val argumentOne = bundleOf(DetailInfoFragment.KEY to item.downloadedPicture)
        findNavController().navigate(R.id.action_mainFragment_to_detailInfo, argumentOne)
    }

}
