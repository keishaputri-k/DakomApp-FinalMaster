package com.kei.dakomapp.room.repository

import android.app.Application
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.room.local.DatabaseLocal
import com.kei.dakomapp.room.local.FavoriteDao
import com.kei.dakomapp.util.ContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteRepository {
    private val favoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val database = DatabaseLocal.databaseFavorite(ContextProvider.get())
        favoriteDao = database.favDao()
    }

    fun getListFavorite(): Flow<List<LectureItem>> {
        return flow {
            val favoriteList = favoriteDao.getFavoritesDao()
            emit(favoriteList)
        }.flowOn(Dispatchers.IO)
    }

    fun insertFavoriteData(lectureItem: LectureItem) {
        executorService.execute { favoriteDao.insertDao(lectureItem) }
    }

    fun deleteFavoritesData(lectureItem: LectureItem) {
        executorService.execute { favoriteDao.deleteDao(lectureItem) }
    }
}