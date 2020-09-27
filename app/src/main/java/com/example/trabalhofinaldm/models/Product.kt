package com.example.trabalhofinaldm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "products")
data class Product (
//var id:Long,

    @ColumnInfo(name= "name")  var name:String,
    @ColumnInfo(name= "price") var price: Double

): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
    override fun toString() = "[$name: $price] "


}