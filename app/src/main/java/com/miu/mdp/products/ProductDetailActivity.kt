package com.miu.mdp.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miu.mdp.R
import com.miu.mdp.model.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        const val TAG = "ProductDetailActivity"
        const val PRODUCT = "product"
        fun newInstance(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT, product)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val product = intent.getParcelableExtra<Product>(PRODUCT)
        product?.let {
            product_image.setImageResource(it.image)
            product_name.text = it.title
            product_color.text = "Color: ${it.color}"
            product_price.text = "Price: $${it.price}"
            product_id.text = "Walmart #: ${it.itemId}"
            product_description.text = it.desc
        }
    }
}