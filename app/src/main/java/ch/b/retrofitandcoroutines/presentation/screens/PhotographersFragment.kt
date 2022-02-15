package ch.b.retrofitandcoroutines.presentation.screens

import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import ch.b.retrofitandcoroutines.presentation.PhotographerAdapter
import ch.b.retrofitandcoroutines.presentation.PhotographerItemClickListener
import androidx.fragment.app.FragmentManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.presentation.MainViewModel
import ch.b.retrofitandcoroutines.presentation.PhotographerUI


class PhotographersFragment : Fragment(), PhotographerItemClickListener {
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

        }) //TODO fix this
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

    override fun onClickPhotographer(photographer: PhotographerUI) {
        val fragment = PhotographerDetailFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()//TODO fix this
        photographer.map(object : PhotographerUI.StringMapper{
            override fun map(id: Int, author: String, URL: String, like: Long, theme: String) {
                Toast.makeText(activity, id.toString(), Toast.LENGTH_LONG).show()
                val bundle = Bundle()//TODO fix this
                bundle.putString("id", id.toString())//TODO fix this
                bundle.putString("author", author)//TODO fix this
                bundle.putString("URL", URL)//TODO fix this
                fragment.arguments = bundle//TODO fix this
            }
            override fun map(message: String) {}
        })

    }
    override fun likeClick(photographer: PhotographerUI) {

    }


/*
    var a = -1
    override fun likeClick(photographer: PhotographerUI) {
        a++
        if (a == 0) {
            Toast.makeText(activity, "+1 лайк пошел на серв", Toast.LENGTH_LONG).show()
            viewModel.pushPost(
                photographer.author,
                photographer.id,
                1,
                photographer.theme,
                photographer.URL
            )
        }
        if (a == 1) {
            Toast.makeText(activity, "-1 лайк пошел на серв", Toast.LENGTH_LONG).show()
            viewModel.pushPost(
                photographer.author,
                photographer.id,
                -1,
                photographer.theme,
                photographer.URL
            )
            a = -1
        }
    }

 */


}