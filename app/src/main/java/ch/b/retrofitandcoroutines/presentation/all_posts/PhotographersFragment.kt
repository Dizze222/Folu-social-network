package ch.b.retrofitandcoroutines.presentation.all_posts

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
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import ch.b.retrofitandcoroutines.core.BasePhotographerStringMapper
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.presentation.all_posts.*
import ch.b.retrofitandcoroutines.presentation.certain_post.PhotographerDetailFragment
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.core.ImageProfile
import ch.b.retrofitandcoroutines.presentation.core.ImageResult
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.data.all_posts.net.Stories
import ch.b.retrofitandcoroutines.data.all_posts.net.Story
import ch.b.retrofitandcoroutines.presentation.all_posts.stories.StoriesContainerAdapter
import javax.inject.Inject


class PhotographersFragment :
    BaseFragment<FragmentPhotographersBinding>(FragmentPhotographersBinding::inflate),
    ImageResult, BackButtonListener {
    @Inject
    lateinit var allPostsViewModelFactory: AllPostsViewModelFactory
    private lateinit var storiesContainerAdapter: StoriesContainerAdapter
    private val viewModel: AllPostsViewModel by viewModels() {
        allPostsViewModelFactory
    }
    private lateinit var photographersAdapter: PhotographerAdapter
    private var imageProfile: ImageProfile = ImageProfile.Empty
    private var searchBy: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.button.setOnClickListener {
        //    (requireActivity() as MainActivity).image()
        //}
        hideNavBar(false)
        storiesContainerAdapter = StoriesContainerAdapter()
        photographersAdapter = PhotographerAdapter(object : PhotographerAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPhotographers()
            }
        },
            object : PhotographerAdapter.PhotographerItemClickListener {
                override fun onClickPhotographer(photographer: PhotographerUI) {
                    val fragment = PhotographerDetailFragment()
                    val fragmentManager = activity!!.supportFragmentManager
                    val nextScreen =
                        ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
                    (parentFragment as ch.b.retrofitandcoroutines.RouterProvider).router.navigateTo(
                        nextScreen
                    )
                    photographer.map(object : BasePhotographerStringMapper.SingleStringMapper {
                        override fun map(
                            id: Int,
                            author: String,
                            URL: String,
                            like: Long,
                            theme: String,
                            comments: List<String>,
                            authorOfComments: List<String>
                        ) {
                            fragmentManager.setFragmentResult("requestKey", bundleOf("id" to id))
                        }

                        override fun map(message: String) = Unit
                        override fun map(progress: Boolean) = Unit
                    })
                }

                override fun likeClick(photographer: PhotographerUI) = Unit

            }
        ) //TODO fix this

        val mergeAdapter = ConcatAdapter(storiesContainerAdapter,photographersAdapter)
        binding.recyclerView.adapter = mergeAdapter
        binding.recyclerView. layoutManager = LinearLayoutManager(activity!!.applicationContext)
        binding.refresh.setOnRefreshListener {
            viewModel.getPhotographers()
            binding.refresh.isRefreshing = false
        }

        lifecycleScope.launchWhenStarted {
            viewModel.observeAllPhotographers(this@PhotographersFragment) {
                photographersAdapter.update(it)
            }
        }
        val list = mutableListOf<Story>()
        list.add(Story(1,"Ivan","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,0))
        list.add(Story(2,"Кирил","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        list.add(Story(3,"Иван","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        list.add(Story(4,"Саша","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        list.add(Story(5,"Маша","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        list.add(Story(6,"Дима","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        list.add(Story(7,"Азат","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        list.add(Story(8,"Григорий","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",true,1))
        storiesContainerAdapter.stories(list)
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
                photographersAdapter.update(it)
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

    fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }

}