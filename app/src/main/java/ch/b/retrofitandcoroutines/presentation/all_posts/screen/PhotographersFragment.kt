package ch.b.retrofitandcoroutines.presentation.all_posts.screen

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerAdapter
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.presentation.all_posts.AllPostsViewModel
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotographersFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var viewModel: AllPostsViewModel
    private lateinit var binding: FragmentPhotographersBinding
    private lateinit var adapter: PhotographerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotographersBinding.bind(view)
        viewModel = (activity?.application as PhotographerApp).allPostsViewModel
        adapter = PhotographerAdapter(object : PhotographerAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPhotographers()
            }
        },
            object : PhotographerAdapter.PhotographerItemClickListener {
                override fun onClickPhotographer(photographer: PhotographerUI) {
                    val fragment = PhotographerDetailFragment()
                    val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                    val fragmentTransaction: FragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.container, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()//TODO fix this
                    photographer.map(object : PhotographerUI.StringMapper {
                        override fun map(
                            id: Int,
                            author: String,
                            URL: String,
                            like: Long,
                            theme: String,
                            comments: List<String>,
                            authorOfComments: List<String>
                        ) {
                            val bundle = Bundle()
                            bundle.putString("id", id.toString())
                            fragment.arguments = bundle
                        }

                        override fun map(message: String) = Unit
                    })
                }

                override fun likeClick(photographer: PhotographerUI) {

                }

            }
        ) //TODO fix this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.refresh.setOnRefreshListener {
            viewModel.getPhotographers()
            binding.refresh.isRefreshing = false
        }

        lifecycleScope.launchWhenStarted {
            viewModel.observeAllPhotographers(this@PhotographersFragment) {
                adapter.update(it)
            }
        }
        viewModel.getPhotographers()


        var count = 2
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    val search = it.actionView as? SearchView
                    search?.isSubmitButtonEnabled = true
                    search?.setOnQueryTextListener(this)
                    true
                }
                R.id.changeCountOfGrid -> {
                    count--
                    binding.recyclerView.layoutManager = GridLayoutManager(activity, count)
                    if (count == 1) {
                        count = 3
                    }
                    true
                }
                else -> false
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photographers, container, false)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchAuthorInDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchAuthorInDatabase(newText)
        }
        return true
    }

    private fun searchAuthorInDatabase(author: String) {
        val searchQuery = "%$author%"
        lifecycleScope.launchWhenStarted {
            viewModel.observeSearchPhotographer(this@PhotographersFragment) {
                adapter.update(it)
            }
        }
        GlobalScope.launch {
            viewModel.searchPhotographers(searchQuery)
        }


    }


}