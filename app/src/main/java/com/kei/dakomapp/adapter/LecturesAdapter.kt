package com.kei.dakomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.kei.dakomapp.R
import org.jetbrains.anko.intentFor
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.ui.DetailActivity
import com.kei.dakomapp.ui.DetailActivity.Companion.EXTRA_LECTURE
import kotlinx.android.synthetic.main.lecture_item.view.*

class LecturesAdapter(var context: Context) : RecyclerView.Adapter<LecturesAdapter.ViewHolder>() {

    private var lectures: List<LectureItem> = ArrayList()


    fun setData(items: List<LectureItem>) {
        lectures = items
        //buat ngereload/syncronize data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: LectureItem) {
            with(itemView) {

//                val urlImg: String =
//                    "https://dakom.kerjainaja.id/image/" + data.posterPhotoPath
//
//                Log.d("Cek DataDi Detail", urlImg)
//                Glide.with(itemView)
//                    .load(urlImg)
//                    .centerCrop()
//                    .into(ivPoster)

                tvTitleLectureCard.text = data.name
                tvLecturerNameCard.text = data.lecturer
                tvLocationCard.text = data.city
                tvDateCard.text = data.date

                itemView.setOnClickListener {
//                    Log.d("Cek Data di adapter", Gson().toJson(data))
//
//                    val page = Intent(context, DetailActivity::class.java)
//                    page.putExtra(DetailActivity.Lectures, Gson().toJson(data))
//                    context.startActivity(page)
                    itemView.context.startActivity(
                        itemView.context.intentFor<DetailActivity>(
                            EXTRA_LECTURE to data
                        )
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: LecturesAdapter.ViewHolder, position: Int) {
        holder.bind(lectures.get(position))

    }

    override fun getItemCount(): Int {
        val size: Int = lectures.size
        // Return at most 5 items from the ArrayList
        return if (size > 5) 5 else size
    }
//    override fun getItemCount(): Int = lectures.size
}