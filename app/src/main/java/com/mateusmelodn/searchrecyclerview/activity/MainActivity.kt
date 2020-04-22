package com.mateusmelodn.searchrecyclerview.activity

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mateusmelodn.searchrecyclerview.R
import com.mateusmelodn.searchrecyclerview.model.Product
import com.mateusmelodn.searchrecyclerview.util.DataHelper
import com.mateusmelodn.searchrecyclerview.util.UIHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_cell.view.*
import java.util.*

class MainActivity: Activity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ProductsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var products: Array<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //****************** SEARCH CONFIGURATION ******************
        setOnClickListener()
        addTextChangedListener()
        //****************** SEARCH CONFIGURATION ******************
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView(DataHelper.generateFakeProducts())
    }

    private fun setOnClickListener() {
        search.setOnClickListener {
            UIHelper.showKeyboardForView(searcher)
        }
        clear_search.setOnClickListener {
            searcher.setText("")
        }
    }

    private fun addTextChangedListener() {
        // Apply dynamic appearance to search component
        searcher.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (searcher.text.isEmpty()) {
                    clear_search.visibility = View.INVISIBLE
                } else {
                    clear_search.visibility = View.VISIBLE
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        // Apply filter to recyclerView items
        searcher.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var newProducts = arrayOf<Product>()
                for (product: Product in products) {
                    if (p0 != null) {
                        if (product.name.toLowerCase(Locale.getDefault()).contains(p0.toString().toLowerCase(Locale.getDefault()))) {
                            newProducts += product
                        }
                    }
                }
                viewAdapter.updateRecyclerView(newProducts)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun initRecyclerView(products: Array<Product>) {
        this.products = products

        // Init recycler view
        viewManager = LinearLayoutManager(this)
        viewAdapter = ProductsAdapter(this.products, this)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    class ProductsAdapter(private var products: Array<Product>, private val activity: MainActivity): RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
        class ProductsViewHolder(root: View) : RecyclerView.ViewHolder(root) {
            val mIdLabel: TextView = root.idLabel
            val mNameLabel: TextView = root.nameLabel
            val mValueLabel: TextView = root.valueLabel
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
            val root = LayoutInflater.from(parent.context).inflate(R.layout.product_cell, parent, false)
            return ProductsViewHolder(root)
        }

        override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
            val product = products[position]
            holder.mIdLabel.text = product.id
            holder.mNameLabel.text = product.name
            holder.mValueLabel.text = product.value

            holder.itemView.setOnClickListener {
                onProductClick(product, holder.itemView)
            }
        }

        private fun onProductClick(product: Product, view: View) {
            val message = "Product clicked: ${product.name}"
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }

        override fun getItemCount() = products.size

        //Update items UI after every character written
        fun updateRecyclerView(newProducts: Array<Product>) {
            products = arrayOf()
            products += newProducts
            notifyDataSetChanged()
        }
    }
}