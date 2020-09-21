package com.example.trabalhofinaldm.util

import com.example.trabalhofinaldm.api.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductServiceGenerator {
    companion object {
        private var retrofit: Retrofit? = null
        private var service: ProductService? = null

        fun getService(): ProductService {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.0.11:3000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                service = retrofit!!.create(ProductService::class.java)
            }
            return service!!
        }
    }
}