package com.kei.dakomapp.room.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.room.repository.FavoriteRepository
import kotlinx.coroutines.launch



class DetailViewModel : ViewModel(){
    private val _detailLecture: MutableLiveData<List<LectureItem?>?> = MutableLiveData()
    val detailLecture get() = _detailLecture

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val loading get() = _loading

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    val error get() = _error

    private var strLecture: String = " "

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite get() = _isFavorite

    private val favoriteRepository: FavoriteRepository by lazy {
        return@lazy FavoriteRepository()
    }

    private val _listFavorite :MutableLiveData<List<LectureItem>?> = MutableLiveData()
    init {
        getFavoriteUser()
        _isFavorite.postValue(false)
    }

    private fun insert(favoriteUser: LectureItem?) {
        viewModelScope.launch {
            if (favoriteUser != null){
                favoriteRepository.insertFavoriteData(favoriteUser)
                getFavoriteUser()
                _isFavorite.postValue(true)
            }
        }
    }

    private fun getFavoriteUser() {
        viewModelScope.launch {
            favoriteRepository.getListFavorite().collect {
                _listFavorite.postValue(it)
            }
        }
    }

    fun showFavorite(favoriteLecture: LectureItem?){
//        getFavoriteUser()
        viewModelScope.launch {
            favoriteRepository.getListFavorite().collect {
                for (item in it){
                    if (favoriteLecture?.id == item.id){
                        _isFavorite.postValue(true)
                        break
                    }else{
                        _isFavorite.postValue(false)
                    }
                }
            }

        }
    }

    private fun delete(favoriteLecture: LectureItem?){
        viewModelScope.launch {
            if (favoriteLecture != null){
                favoriteRepository.deleteFavoritesData(favoriteLecture)
                getFavoriteUser()
                _isFavorite.postValue(false)
            }
        }
    }

    fun isFavoriteLecture(favoriteLecture: LectureItem?){
        viewModelScope.launch {
            if (_isFavorite.value == true){
                delete(favoriteLecture)
            }else{
                insert(favoriteLecture)
            }
        }
    }
}