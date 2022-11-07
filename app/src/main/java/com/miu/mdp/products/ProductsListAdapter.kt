package com.miu.mdp.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miu.mdp.R
import com.miu.mdp.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsListAdapter(private val onClick: OnItemClickListener) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    private var products: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.product_card.setOnClickListener {
                onClick.onItemClick(product)
            }
            itemView.product_name.text = product.title
            itemView.product_color.text = "Color: ${product.color}"
            itemView.product_price.text = "Price: $${product.price}"
            itemView.product_image.setImageResource(product.image)
        }
    }

    //click listener
    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }
}
