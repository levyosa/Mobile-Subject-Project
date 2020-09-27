package com.example.trabalhofinaldm.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trabalhofinaldm.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<Product>

    @Query("SELECT * FROM products WHERE id IN (:ids)")
    fun getAllByIds(ids:IntArray):List<Product>
    

    @Insert
    fun insertAll(varargpeople:Product)

    @Delete
    fun delete(product: Product)



}