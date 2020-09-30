package com.example.trabalhofinaldm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.trabalhofinaldm.interfaces.ProductAdapterListener
import com.example.trabalhofinaldm.models.Product

class MainActivity : AppCompatActivity(),ProductAdapterListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onProductClicked(product: Product) {
        Toast.makeText(this, "adicionado", Toast.LENGTH_SHORT).show()
        Log.d("toast",product.name)
    }
}