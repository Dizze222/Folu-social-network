package ch.b.retrofitandcoroutines.presentation.all_posts.screen


import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager

import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.presentation.all_posts.*
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.core.ImageProfile
import ch.b.retrofitandcoroutines.presentation.core.ImageResult
import ch.b.retrofitandcoroutines.presentation.navigate.BackButtonListener
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.terrakok.cicerone.commands.Back

@AndroidEntryPoint
class PhotographersFragment : Fragment(), ImageResult,BackButtonListener {
    private val viewModel: AllPostsViewModel by viewModels()
    private lateinit var binding: FragmentPhotographersBinding
    private lateinit var adapter: PhotographerAdapter
    private var imageProfile: ImageProfile = ImageProfile.Empty
    private var searchBy: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotographersBinding.bind(view)

        //binding.button.setOnClickListener {
        //    (requireActivity() as MainActivity).image()
        //}
        val navBar = activity!!.findViewById<View>(R.id.navigation)
        navBar.visibility = View.VISIBLE
        adapter = PhotographerAdapter(object : PhotographerAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPhotographers()
            }
        },
            object : PhotographerAdapter.PhotographerItemClickListener {
                override fun onClickPhotographer(photographer: PhotographerUI) {
                    val fragment = PhotographerDetailFragment()
                    val nextScreen = FragmentScreen(fragment.newInstance())
                    val bundle = Bundle()
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

                            bundle.putString("id", id.toString())
                            fragment.arguments = bundle
                            (parentFragment as RouterProvider).router.navigateTo(nextScreen)
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
        setupListeners()
    }

    private fun setupListeners() {
        binding.search.addTextChangedListener(textChangeListener)
        binding.cancelSearch.setOnClickListener { binding.search.text.clear() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photographers, container, false)
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
    fun newInstance() : PhotographersFragment{
        return PhotographersFragment()
    }

    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }
}