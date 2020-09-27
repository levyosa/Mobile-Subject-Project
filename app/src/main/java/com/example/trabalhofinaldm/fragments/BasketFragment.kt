package com.example.trabalhofinaldm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhofinaldm.R
import com.example.trabalhofinaldm.adapters.ProductAdapter
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.models.Product
import kotlinx.android.synthetic.main.fragment_basket.view.*

class BasketFragment : Fragment(),ProductAdapterListener {
    private lateinit var adapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_basket, container, false)


        adapter = ProductAdapter(this,requireContext())
        view.rvBasket.adapter = adapter
        view.rvBasket.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)






        return view
    }

    override fun onProductClicked(product: Product) {

    }


}