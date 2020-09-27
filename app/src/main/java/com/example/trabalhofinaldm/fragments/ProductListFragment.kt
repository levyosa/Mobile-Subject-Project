package com.example.trabalhofinaldm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhofinaldm.R
import com.example.trabalhofinaldm.adapters.ProductAdapter
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.models.Product
import kotlinx.android.synthetic.main.fragment_product_list.view.*


class ProductListFragment : Fragment(),ProductAdapterListener {
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_product_list, container, false)



        adapter = ProductAdapter(this)
        view.rvProducts.adapter = adapter
        view.rvProducts.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        view.btBasket.setOnClickListener{
            findNavController().navigate(R.id.navigateTo_BasketFragment)
        }


        return view
    }

    override fun onProductClicked(product: Product) {

    }


}