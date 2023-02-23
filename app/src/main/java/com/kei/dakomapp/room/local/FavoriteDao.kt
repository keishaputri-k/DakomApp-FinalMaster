package com.kei.dakomapp.room.local

import androidx.room.*
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.model.ResponseLectures

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDao (lectureItem: LectureItem)

    @Query("SELECT * from lectureItem")
    fun getFavoritesDao() :List<LectureItem>

    @Delete
    fun deleteDao(lectureItem: LectureItem)
}