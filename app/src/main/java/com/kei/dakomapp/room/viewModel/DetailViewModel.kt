package com.kei.dakomapp.room.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.room.repository.FavoriteRepository
import com.kei.dakomapp.room.repository.LectureRepository
import com.kei.dakomapp.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val lectureRepository: LectureRepository, private val favoriteRepository: FavoriteRepository): ViewModel(){
    private val _detailLecture: MutableLiveData<List<LectureItem?>?> = MutableLiveData()
    val detailLecture get() = _detailLecture

    private val _loading : MutableLiveData<Boolean> = MutableLiveData()
    val loading get() = _loading

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    val error get() = _error

    private var strLecture: String = " "

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite get() = _isFavorite

    private val _listFavorite :MutableLiveData<List<LectureItem>?> = MutableLiveData()
    init {
        getFavoriteUser()
        _isFavorite.postValue(false)
    }

    fun getDetailUser(lectures: String) {
        if (strLecture != lectures) {
            viewModelScope.launch {
                strLecture = lectures
                lectureRepository.getAllLectures().onStart {
                    _loading.value = true
                }.onCompletion {
                    _loading.value = false
                }.collect {
                    when (it) {
                        is NetworkResult.Success -> {
                            _error.postValue(null)
                            _detailLecture.postValue(it.data)
                        }
                        is NetworkResult.Error -> {
                            _error.postValue(it.throwable)
                        }
                    }
                }
            }
        }
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
        viewModelScope.launch {
            for (it in _listFavorite.value?: mutableListOf()){
                if (favoriteLecture?.name == it.name){
                    _isFavorite.postValue(true)
                    break
                }else{
                    _isFavorite.postValue(false)
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

    fun isFavoriteUser(favoriteLecture: LectureItem?){
        viewModelScope.launch {
            if (_isFavorite.value == true){
                delete(favoriteLecture)
            }else{
                insert(favoriteLecture)
            }
        }
    }
}