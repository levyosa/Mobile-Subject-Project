package com.example.trabalhofinaldm.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.trabalhofinaldm.R
import com.example.trabalhofinaldm.database.AppDatabase
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.interfaces.ProductDao
import com.example.trabalhofinaldm.models.Product
import com.example.trabalhofinaldm.util.ProductServiceGenerator
import kotlinx.android.synthetic.main.item_product.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAdapter(val listener: ProductAdapterListener,context: Context):
    RecyclerView.Adapter<ProductAdapter.ViewHolder> () {
    private var products = mutableListOf<Product>()
    private val service = ProductServiceGenerator.getService()

    private var dao: ProductDao
    private var handledProduct: Product? = null
    private var basketProducts = mutableListOf<Product>()






    init {

        ////////////////////////////////// RETROFIT
        service.getAll().enqueue(object: Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                products = response.body()!!.toMutableList()
                notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Log.d("enqueue","failed",t)
            }
        })

        //////////////////////////////////////////

        //////////////////////////////////ROOM
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "local-products-db"
        ).allowMainThreadQueries().build()
        dao = db.productDao()
        basketProducts = dao.getAll().toMutableList()

        //////////////////////////////////////////




    }


    override fun getItemViewType(position: Int): Int = R.layout.item_product


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(viewType,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.fillView(product)
    }

    override fun getItemCount(): Int = products.size



    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


        fun fillView(product: Product) {
            itemView.tvProductName.text = product.name
            itemView.tvProductPrice.text = product.price.toString()


            itemView.setOnClickListener{
                handledProduct = product
                var dummy = Product(handledProduct!!.name,handledProduct!!.price)
                dao.insertAll(dummy)
                Log.d("dao","["+dao.getAll().size+"]"+dao.getAll().toString())
                listener.onProductClicked(dummy)


            }


        }

    }



}