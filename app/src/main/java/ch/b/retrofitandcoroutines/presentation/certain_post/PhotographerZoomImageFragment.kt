package ch.b.retrofitandcoroutines.presentation.certain_post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.b.retrofitandcoroutines.R
import com.bumptech.glide.Glide
import com.zolad.zoominimageview.ZoomInImageView


class PhotographerZoomImageFragment : Fragment() {

    lateinit var imageView: ZoomInImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       imageView = view.findViewById(R.id.imageOfAuthor)

        val bundle = this.arguments
        val url = bundle!!.getString("URL")
        Glide.with(imageView)
            .load(url)
            .into(imageView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_full_image_view, container, false)
    }

}