package com.nguyen.doordash2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.doordash2.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var binding: ActivityMainBinding
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stores = mutableListOf<Store>()
        val adapter = StoresAdapter(stores, object : StoresAdapter.OnClickListener {
            override fun onClick(position: Int) {
                val store = stores[position]
                val intent = Intent(this@MainActivity, StoreActivity::class.java)
                intent.putExtra("EXTRA_STORE", store)
                startActivity(intent)
            }
        })
        binding.rvStores.adapter = adapter
        binding.rvStores.layoutManager = LinearLayoutManager(this)

        viewModel.getStores().observe(this, Observer {
            Log.d(TAG, "Observer received ${it!!.size} stores")
            stores.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
}