package ch.b.retrofitandcoroutines.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import ch.b.retrofitandcoroutines.presentation.PhotographerAdapter
import androidx.fragment.app.FragmentManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.presentation.MainViewModel
import ch.b.retrofitandcoroutines.presentation.PhotographerUI
import io.realm.RealmList


class PhotographersFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentPhotographersBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotographersBinding.bind(view)
        viewModel = (activity?.application as PhotographerApp).mainViewModel
        val adapter = PhotographerAdapter(object : PhotographerAdapter.Retry {
            override fun tryAgain() {
                viewModel.getPhotographers()
            }
        },
            object : PhotographerAdapter.PhotographerItemClickListener {
                override fun onClickPhotographer(photographer: PhotographerUI) {
                    val fragment = PhotographerDetailFragment()
                    val fragmentManager: FragmentManager = activity!!.supportFragmentManager
                    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentContainer, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()//TODO fix this
                    photographer.map(object : PhotographerUI.StringMapper {
                        override fun map(
                            id: Int,
                            author: String,
                            URL: String,
                            like: Long,
                            theme: String,
                            comments: RealmList<String>,
                            authorOfComments: RealmList<String>
                        ) {
                            val array = arrayListOf<String>()
                            val arrayAuthorOfComments = arrayListOf<String>()
                            val bundle = Bundle()//TODO fix this
                            bundle.putString("id", id.toString())//TODO fix this
                            bundle.putString("author", author)//TODO fix this
                            bundle.putString("URL", URL)//TODO fix this
                            for (i in comments){
                                array.add(i)
                            }
                            for (i in authorOfComments){
                                arrayAuthorOfComments.add(i)
                            }
                            bundle.putStringArrayList("CUM",array)
                            bundle.putStringArrayList("CUMauthor",arrayAuthorOfComments)
                            fragment.arguments = bundle//TODO fix this
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


        viewModel.observe(this) {
            adapter.update(it)
        }
        viewModel.getPhotographers()

        var count = 2
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    viewModel.getPhotographers()
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


}