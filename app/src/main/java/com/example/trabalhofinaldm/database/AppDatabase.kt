package com.example.trabalhofinaldm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trabalhofinaldm.interfaces.ProductDao
import com.example.trabalhofinaldm.models.Product

@Database(entities = [Product::class],version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun productDao(): ProductDao

}