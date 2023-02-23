package com.kei.dakomapp.room.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kei.dakomapp.model.LectureItem
import com.kei.dakomapp.ui.DetailActivity

@Database(entities = [LectureItem::class], version = 1)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun favDao(): FavoriteDao
    companion object{
        @Volatile
        var instance : DatabaseLocal? = null

        fun databaseFavorite(context: Context) : DatabaseLocal{
            if(instance == null){
                synchronized(DatabaseLocal::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext, DatabaseLocal::class.java,"Favorite"
                    ).build()
                }
            }
           return instance as DatabaseLocal
        }
    }
}