package ru.nb.mish.photostudioex.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_image.view.*
import ru.nb.mish.photostudioex.R

class ImageAdapter(private var onImageClick: (String) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mData: List<String>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder = object : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)) {}

        viewHolder.itemView.setOnClickListener { onImageClick(mData!![viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return mData?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val imageUrl = mData!![position] // получаем из списка нужную картинку

        Glide.with(holder.itemView)
                .load(imageUrl)
                .apply(RequestOptions().centerCrop())
                .into(holder.itemView.imageView)
    }
}