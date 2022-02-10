package com.example.advanceandroid.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDao

}