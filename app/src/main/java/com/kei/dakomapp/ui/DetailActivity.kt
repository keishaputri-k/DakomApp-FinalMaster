package com.kei.dakomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.kei.dakomapp.R
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.room.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModels()
    var isFav = false


    companion object {
        const val EXTRA_LECTURE = "extra_lectures"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()


        val data = intent.getParcelableExtra(EXTRA_LECTURE) as? LectureItem
//        var detail:LectureItem = Gson().fromJson(data,LectureItem::class.java)

        val photo = Glide.with(this).load(data?.posterPhotoPath).centerCrop().into(ivPoster).toString()

        Log.d("get photo", photo)
        tvTitleDetail.text = data?.name.toString()
        tvLecturerDetail.text = data?.lecturer.toString()
        tvCategoriesDetail.text = data?.category.toString()
        tvTypeDetail.text = data?.type.toString()
        tvLocationDetail.text = data?.location.toString()
        tvDateDetail.text = data?.date.toString()
        tvTimeDetail.text = data?.date.toString()
        tvCpDetail.text = data?.cp.toString()
        tvDescDetail.text = data?.description.toString()
        tvQuotaDetail.text = data?.quota.toString()

        detailViewModel.showFavorite(data)
        observeData()

        btnSaveDetail.setOnClickListener {
            data?.let { it1 -> detailViewModel.isFavoriteLecture(it1)}
        }


    }

    private fun observeData() {
        detailViewModel.isFavorite.observe(this) { isFav ->
            this.isFav = isFav
            btnSaveDetail.setImageResource(if (isFav) R.drawable.ic_save_active else R.drawable.ic_save_unactive)
        }
    }


}