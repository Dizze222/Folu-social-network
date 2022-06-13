package ch.b.retrofitandcoroutines.testscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.testscreen.databinding.FragmentTestBinding

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestBinding.bind(view)
        binding.textView.setOnClickListener{
            val fragment = TestFragment()
            val nextScreen = ch.b.retrofitandcoroutines.FragmentScreen(fragment.newInstance())
            (parentFragment as ch.b.retrofitandcoroutines.RouterProvider).router.navigateTo(nextScreen)
        }
    }
    fun newInstance() : TestFragment{
        return TestFragment()
    }
}