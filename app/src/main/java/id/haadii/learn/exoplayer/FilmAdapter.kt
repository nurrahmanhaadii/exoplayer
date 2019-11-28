package id.haadii.learn.exoplayer

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import id.haadii.learn.exoplayer.data.model.Hits
import kotlinx.android.synthetic.main.item_film.view.*
import java.util.*

class FilmAdapter(private val items: ArrayList<Hits>, private val listener: (Hits) -> Unit) :
    RecyclerView.Adapter<FilmAdapter.FilmAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapterViewHolder {
        return FilmAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_film,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilmAdapterViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class FilmAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Hits, listener: (Hits) -> Unit) {

            Glide.with(itemView.context)
                .asBitmap()
                .load(item.videos.medium.url)
                .placeholder(R.drawable.progress_animation)
                .into(itemView.iv_image)

            itemView.tv_user.text = item.user
            itemView.tv_duration.text = "0:" + item.duration.toString()

            itemView.setOnClickListener {
                listener(item)
            }
        }
    }
}