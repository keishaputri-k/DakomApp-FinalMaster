package com.kei.dakomapp.room.repository

import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.util.network.ApiEndPoint
import com.kei.dakomapp.util.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LectureRepository @Inject constructor(private val apiEndPoint: ApiEndPoint) {
    suspend fun getAllLectures(): Flow<NetworkResult<List<LectureItem?>?>>{
        return flow {
            try {
                val data = apiEndPoint.getDataFav().lecture
                emit(NetworkResult.Success(data))
            }catch (e: Throwable){
                emit(NetworkResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

}