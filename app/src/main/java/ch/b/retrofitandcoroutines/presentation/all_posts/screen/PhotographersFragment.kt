package ch.b.retrofitandcoroutines.presentation.all_posts.screen

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.core.BasePhotographerStringMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.*
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.core.ImageProfile
import ch.b.retrofitandcoroutines.presentation.core.ImageResult
import ch.b.retrofitandcoroutines.presentation.navigate.BackButtonListener
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotographersFragment : BaseFragment<FragmentPhotographersBinding>(FragmentPhotographersBinding::inflate),
    ImageResult, BackButtonListener {
    private val viewModel: AllPostsViewModel by viewModels()
    private lateinit var adapter: PhotographerAdapter
    private var imageProfile: ImageProfile = ImageProfile.Empty
    private var searchBy: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.button.setOnClickListener {
        //    (requireActivity() as MainActivity).image()
        //}
        hideNavBar(false)
        adapter = PhotographerAdapter(object : PhotographerAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPhotographers()
            }
        },
            object : PhotographerAdapter.PhotographerItemClickListener {
                override fun onClickPhotographer(photographer: PhotographerUI) {
                    val fragment = PhotographerDetailFragment()
                    val fragmentManager = activity!!.supportFragmentManager
                    val nextScreen = FragmentScreen(fragment.newInstance())
                    (parentFragment as RouterProvider).router.navigateTo(nextScreen)
                    photographer.map(object : BasePhotographerStringMapper.SingleStringMapper {
                        override fun map(id: Int, author: String, URL: String, like: Long, theme: String, comments: List<String>, authorOfComments: List<String>) {
                            fragmentManager.setFragmentResult("requestKey", bundleOf("id" to id))
                        }

                        override fun map(message: String) = Unit
                    })
                }

                override fun likeClick(photographer: PhotographerUI) = Unit

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
        setupListeners()
    }

    private fun setupListeners() {
        binding.search.addTextChangedListener(textChangeListener)
        binding.cancelSearch.setOnClickListener { binding.search.text.clear() }
    }

    private val textChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isEmpty()) {
                binding.cancelSearch.visibility = View.GONE
            } else {
                binding.cancelSearch.visibility = View.VISIBLE
            }
            searchBy = s.toString()
            if (binding.search.hasFocus()) {
                searchAuthorInDatabase(searchBy)
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit

    }

    private fun searchAuthorInDatabase(author: String) {
        val searchQuery = "%$author%"
        lifecycleScope.launchWhenStarted {
            viewModel.observeSearchPhotographer(this@PhotographersFragment) {
                adapter.update(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.searchPhotographers(searchQuery)
        }
    }

    override fun onImageResult(uri: Uri) {
        //binding.button.setImageURI(uri)
        imageProfile = ImageProfile.Base(uri)
    }

    fun newInstance(): PhotographersFragment {
        return PhotographersFragment()
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}