package com.kei.dakomapp.ui.mainFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kei.dakomapp.R
import com.kei.dakomapp.adapter.LecturesAdapter
import com.kei.dakomapp.databinding.FragmentMyLecturesBinding
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.room.repository.FavoriteRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_lectures.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//@AndroidEntryPoint
class MyLecturesFragment : Fragment() {

//    private lateinit var lectures: List<LectureItem>
    private lateinit var lecturesAdapter: LecturesAdapter
//    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_lectures, container, false)
        val rvMyLectures = view.findViewById<RecyclerView>(R.id.rvMyLectures)

        lecturesAdapter = LecturesAdapter(requireContext())

        showRecyclerView(rvMyLectures)
        observeData()

        return view
    }

    private fun showRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = lecturesAdapter
        }
    }

   private fun observeData() {
       CoroutineScope(Dispatchers.Main).launch {
           FavoriteRepository().getListFavorite().collect {
               lecturesAdapter.setData(it)
               Log.d("MY LECTURES", it.toString())
           }
       }
   }


    companion object {
        const val EXTRA_DATA = "extra_data"
        @JvmStatic
        fun newInstance() =
            MyLecturesFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
