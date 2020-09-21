package com.example.trabalhofinaldm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhofinaldm.R
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.models.Product
import com.example.trabalhofinaldm.util.ProductServiceGenerator
import kotlinx.android.synthetic.main.item_product.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductAdapter(private val listener: ProductAdapterListener):
    RecyclerView.Adapter<ProductAdapter.ViewHolder> () {
    private var products = mutableListOf<Product>()
    private val service = ProductServiceGenerator.getService()


    init {
        service.getAll().enqueue(object: Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                products = response.body()!!.toMutableList()
                notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }
        })

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

            }
        }

    }


}