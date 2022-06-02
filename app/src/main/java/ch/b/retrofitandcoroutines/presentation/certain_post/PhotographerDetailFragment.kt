package ch.b.retrofitandcoroutines.presentation.certain_post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.ImageLoad
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographerDetailBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.viewModels
import ch.b.retrofitandcoroutines.presentation.all_posts.screen.PhotographersFragment
import ch.b.retrofitandcoroutines.presentation.navigate.BackButtonListener
import ch.b.retrofitandcoroutines.presentation.navigate.RouterProvider
import dagger.hilt.android.AndroidEntryPoint
import androidx.annotation.NonNull

import androidx.fragment.app.FragmentResultListener


@AndroidEntryPoint
class PhotographerDetailFragment : Fragment(), BackButtonListener {
    private lateinit var binding: FragmentPhotographerDetailBinding
    private val certainViewModel: CertainPostViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotographerDetailBinding.bind(view)
        val bundle = this.arguments
        val fragment = PhotographerZoomImageFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        var photographerId: String?
        fragmentManager.setFragmentResultListener("requestKey", this, { key, bundle ->
            photographerId = bundle.getString("id")
            val adapter = CommentsAdapter()
            val navBar = activity!!.findViewById<View>(R.id.navigation)
            navBar.visibility = View.GONE
            binding.commetsRecyclerView.adapter = adapter
            binding.commetsRecyclerView.layoutManager = GridLayoutManager(activity, 1)
            lifecycleScope.launchWhenStarted {
                certainViewModel.observeCertainPost(this@PhotographerDetailFragment) {
                    it.map { post ->
                        post.map(object : PhotographerUI.StringMapper {
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
                                    convertToArrayList(comments),
                                    convertToArrayList(authorOfComments)
                                )
                                ImageLoad.Base(URL).load(binding.imageOfAuthor)
                                bundle!!.putString("URL", URL)


                            }

                            override fun map(message: String) = Unit

                        })
                    }
                }

            }

            certainViewModel.getCertainPost(photographerId!!.toInt())
        }
        )

        fragment.arguments = bundle
        binding.imageOfAuthor.setOnClickListener {
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photographer_detail, container, false)
    }

    fun newInstance(): PhotographerDetailFragment {
        return PhotographerDetailFragment()
    }


    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }
}

fun convertToArrayList(list: List<String>): ArrayList<String> {
    val someList = arrayListOf<String>()
    for (i in list) {
        someList.add(i)
    }
    return someList
}