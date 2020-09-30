package com.example.trabalhofinaldm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.trabalhofinaldm.R
import com.example.trabalhofinaldm.adapters.BasketProductAdapter
import com.example.trabalhofinaldm.database.AppDatabase
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.interfaces.ProductDao
import com.example.trabalhofinaldm.models.Product
import kotlinx.android.synthetic.main.fragment_basket.view.*

class BasketFragment : Fragment(),ProductAdapterListener {
    private lateinit var adapter: BasketProductAdapter
    private var subtotal = 0.0
    private lateinit var dao: ProductDao
    private var basketProducts = mutableListOf<Product>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_basket, container, false)


        adapter = BasketProductAdapter(this,requireContext())
        view.rvBasket.adapter = adapter
        view.rvBasket.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)



        calculateSubtotal()
        view.tvTotalPrice.setText(subtotal.toString())



        return view
    }

    override fun onProductClicked(product: Product) {

    }

    fun calculateSubtotal(){
        initDAO()
        subtotal = 0.0

        basketProducts.forEach(){
           subtotal += it.price
        }
    }

    fun initDAO(){
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "local-products-db"
        ).allowMainThreadQueries().build()
        dao = db.productDao()
        basketProducts = dao.getAll().toMutableList()
    }


}