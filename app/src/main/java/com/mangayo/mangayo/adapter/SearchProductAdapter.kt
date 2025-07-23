package com.mangayo.mangayo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mangayo.mangayo.R
import com.mangayo.mangayo.model.Product

class SearchProductAdapter(private val products:ArrayList<Product>) :
    RecyclerView.Adapter<SearchProductAdapter.CustomViewHolder>() {

    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product_search, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val product = products[position]

        val titleText = holder.view.findViewById<TextView>(R.id.productName)
        titleText.text = product.name

        val priceText = holder.view.findViewById<TextView>(R.id.productPrice)
        priceText.text = product.getPriceString()

    }

    override fun getItemCount() = products.size
}