package com.nguyen.doordash2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nguyen.doordash2.databinding.ItemStoreBinding

private const val TAG = "StoresAdapter"
class StoresAdapter(private val context: Context, private val stores: List<Store>): RecyclerView.Adapter<StoresAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemStoreBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(store: Store) {
            val roundedCorners = RequestOptions().transform(CenterCrop(), RoundedCorners(20))
            Glide.with(context).load(store.coverImgUrl).apply(roundedCorners).into(binding.image)
            binding.name.text = store.name
            binding.description.text = store.description
            binding.deliveryFee.text = store.deliveryFee
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val store = stores[adapterPosition]
                val intent = Intent(context, RestaurantActivity::class.java)
                intent.putExtra("EXTRA_STORE", store)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemStoreBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = stores[position]
        holder.bind(store)
    }

    override fun getItemCount() = stores.size
}
