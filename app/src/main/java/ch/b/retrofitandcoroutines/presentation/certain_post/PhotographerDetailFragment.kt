package ch.b.retrofitandcoroutines.presentation.certain_post

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.presentation.core.ImageLoad
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographerDetailBinding
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.viewModels
import ch.b.retrofitandcoroutines.core.BasePhotographerStringMapper
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.core.convertToArrayList
import ch.b.retrofitandcoroutines.presentation.navigate.BackButtonListener
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import javax.inject.Inject


class PhotographerDetailFragment :
    BaseFragment<FragmentPhotographerDetailBinding>(FragmentPhotographerDetailBinding::inflate),
    BackButtonListener {
    @Inject
    lateinit var certainViewModelFactory: CertainViewModelFactory
    private val certainViewModel: CertainPostViewModel by viewModels() {
        certainViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        var photographerId: Int?
        fragmentManager.setFragmentResultListener("requestKey", this, { key, bundle ->
            photographerId = bundle.getInt("id")
            val adapter = CommentsAdapter()
            hideNavBar(true)
            binding.commetsRecyclerView.adapter = adapter
            binding.commetsRecyclerView.layoutManager = GridLayoutManager(activity, 1)
            lifecycleScope.launchWhenStarted {
                certainViewModel.observeCertainPost(this@PhotographerDetailFragment) {
                    it.map { post ->
                        post.map(object : BasePhotographerStringMapper.SingleStringMapper {
                            override fun map(
                                id: Int,
                                author: String,
                                URL: String,
                                like: Long,
                                theme: String,
                                comments: List<String>,
                                authorOfComments: List<String>
                            ) {
                                binding.idOfAuthor.text = id.toString()
                                binding.author.text = author
                                binding.toolbar.title = author
                                adapter.update(
                                    comments.convertToArrayList(),
                                    authorOfComments.convertToArrayList()
                                )
                                ImageLoad.Base(URL).load(binding.imageOfAuthor)
                                bundle.putString("URL", URL)
                            }

                            override fun map(message: String) {
                                binding.toolbar.title = message
                            }

                            override fun map(progress: Boolean) {
                                binding.toolbar.title = "Загрузка"
                            }
                        })
                    }
                }

            }

            certainViewModel.getCertainPost(photographerId!!.toInt())
        }
        )
    }


    fun newInstance(): PhotographerDetailFragment {
        return PhotographerDetailFragment()
    }


    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }

    fun inject(){
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }

}