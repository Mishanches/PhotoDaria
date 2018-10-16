package ru.nb.mish.photostudioex.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_full_image.*

import ru.nb.mish.photostudioex.R
import ru.nb.mish.photostudioex.components.IntentHelper

class FullImageFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this)
                .load(arguments?.getString(IntentHelper.PHOTO_URL))

                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        imageView.setImageResource(R.drawable.ic_no_image)
                        return true
                    }
                })
                .into(imageView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_full_image, container, false)
    }

    companion object {
        fun newInstance(imageUrl: String): FullImageFragment {
            val fragment = FullImageFragment()
            val args = Bundle()
            args.putString(IntentHelper.PHOTO_URL, imageUrl)
            fragment.arguments = args
            return fragment
        }
    }
}