package com.kei.dakomapp.room.viewModel

import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.room.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository): ViewModel(){
    private val _favoriteLiveData : MutableLiveData<List<LectureItem>?> = MutableLiveData()
    val favoriteLiveData get() = _favoriteLiveData

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    val error get()  = _error

    init {
        getDataListFavorite()
    }

    fun  getDataListFavorite(){
        viewModelScope.launch {
            favoriteRepository.getListFavorite().collect{
                _favoriteLiveData.postValue(it)
            }
        }
    }

    fun insertFavoriteData(lectureItem: LectureItem) {
        favoriteRepository.insertFavoriteData(lectureItem)
    }

    fun deleteFavoritesData(lectureItem: LectureItem) {
        favoriteRepository.deleteFavoritesData(lectureItem)
    }

}