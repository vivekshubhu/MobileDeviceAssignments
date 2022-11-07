package com.miu.mdp.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.mdp.R
import com.miu.mdp.model.Product
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductsListAdapter.OnItemClickListener {

    companion object {
        const val TAG = "ProductActivity"
        const val PRODUCTLIST = "productList"
        fun newInstance(context: Context, productList: ArrayList<Product>): Intent {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra(PRODUCTLIST, productList)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        val adapter = ProductsListAdapter(this)
        product_list.layoutManager = LinearLayoutManager(this)
        product_list.adapter = adapter
        val productList = intent.getParcelableArrayListExtra<Product>(PRODUCTLIST)
        productList?.let {
            adapter.setProducts(it)
        }
    }

    override fun onItemClick(product: Product) {
        val intent = ProductDetailActivity.newInstance(this, product)
        startActivity(intent)
    }
}