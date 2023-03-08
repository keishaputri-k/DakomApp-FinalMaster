package com.kei.dakomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kei.dakomapp.R
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.ui.DetailActivity
import kotlinx.android.synthetic.main.lecture_item.view.*
import org.jetbrains.anko.intentFor


class EventsAdapter (var context: Context) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
    private var lectures: List<LectureItem> = ArrayList()

    fun setData(items: List<LectureItem>) {
        lectures = items
        //buat ngereload/syncronize data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: LectureItem) {
            with(itemView) {

                tvTitleLectureCard.text = data.name
                tvLecturerNameCard.text = data.lecturer
                tvLocationCard.text = data.city
                tvDateCard.text = data.date

                itemView.setOnClickListener {
                    itemView.context.startActivity(
                        itemView.context.intentFor<DetailActivity>(
                            DetailActivity.EXTRA_LECTURE to data
                        )
                    )
                }
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lecture_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: EventsAdapter.ViewHolder, position: Int) {
        holder.bind(lectures.get(position))

    }

    override fun getItemCount(): Int = lectures.size
}