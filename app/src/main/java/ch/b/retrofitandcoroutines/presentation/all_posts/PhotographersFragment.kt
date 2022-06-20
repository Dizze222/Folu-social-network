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
                    photographer.map(object : BasePhotographerStringMapper.IdMapper {
                        override fun map(id: Int) {
                            fragmentManager.setFragmentResult("requestKey", bundleOf("id" to id))
                        }
                    })
                }

                override fun likeClick(photographer: PhotographerUI) = Unit

            }
        )

        val mergeAdapter = ConcatAdapter(storiesContainerAdapter,photographersAdapter)
        binding.recyclerView.adapter = mergeAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
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
        list.add(Story(1,"Ivan","https://rkorova.ru/assets/images/portfolio/myagkaya-igrushka-talisman-08.jpg",false,0))
        list.add(Story(2,"Кирил","https://icdn.lenta.ru/images/2019/10/22/09/20191022091418639/pwa_vertical_1280_735a83106d6f25775db643a0e1ac8dfb.jpg",true,1))
        list.add(Story(3,"Иван","https://myaw.by/upload/iblock/0d5/0d586c8df2382335a475205e7bbe694a.png",true,1))
        list.add(Story(4,"Саша","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQLB1ddsrLGCCnFTRcWKiSZuO_1VwGKu9wgxOQenLki5dapre23YTpSPYP2G7rMDV6lZWk&usqp=CAU",true,1))
        list.add(Story(5,"Маша","https://cdn.botanichka.ru/wp-content/uploads/2020/05/kroliki-na-leto-moy-opyit-razvedeniya-01.jpg",true,1))
        list.add(Story(6,"Дима","https://avatars.mds.yandex.net/get-zen_doc/4569048/pub_61bd850c4b46c01ca7146949_61bd86baacfe143bb4cdc6f2/scale_1200",false,1))
        list.add(Story(7,"Азат","https://omskzdes.ru/storage/c/2019/11/11/1573447270_423998_15.jpg",false,1))
        list.add(Story(8,"Григорий","https://storage-api.petstory.ru/resize/800x800x80/fa/17/4b/fa174b369e114c428c0601cb64369c85.jpeg",false,1))
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