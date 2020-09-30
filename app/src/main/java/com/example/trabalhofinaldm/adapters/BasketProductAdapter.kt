package com.example.trabalhofinaldm.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.trabalhofinaldm.R
import com.example.trabalhofinaldm.database.AppDatabase
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.interfaces.ProductDao
import com.example.trabalhofinaldm.models.Product
import kotlinx.android.synthetic.main.fragment_basket.view.*
import kotlinx.android.synthetic.main.item_product.view.*



class BasketProductAdapter(val listener: ProductAdapterListener, context: Context):
    RecyclerView.Adapter<BasketProductAdapter.ViewHolder> () {
    private var dao: ProductDao
    private var handledProduct: Product? = null
    private var basketProducts = mutableListOf<Product>()


    init {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "local-products-db"
        ).allowMainThreadQueries().build()
        dao = db.productDao()
        basketProducts = dao.getAll().toMutableList()

    }


    override fun getItemViewType(position: Int): Int = R.layout.item_product

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(viewType,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = basketProducts[position]
        holder.fillView(product)
    }

    override fun getItemCount(): Int = basketProducts.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fillView(product: Product) {
            itemView.tvProductName.text = product.name
            itemView.tvProductPrice.text = product.price.toString()



            itemView.setOnClickListener{
                handledProduct = product
                var dummy:Product = Product(handledProduct!!.name,handledProduct!!.price)
                dao.insertAll(dummy)
                Log.d("dao","["+dao.getAll().size+"]"+dao.getAll().toString())

            }




        }
    }
}