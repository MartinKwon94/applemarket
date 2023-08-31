package com.example.applemarket

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemListBinding

class ItemAdapter(private val mitems: MutableList<SaleItem>) :
    RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.Holder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        holder.itemImageView.setImageResource(mitems[position].Image)
        holder.tvItemTitle.text = mitems[position].ItemTitle
        holder.tvAddress.text = mitems[position].Address

        val price = mitems[position].Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price) + "원"

        holder.tvItemChat.text = mitems[position].ChatCnt.toString()
        holder.tvItemLike.text = mitems[position].InterestCnt.toString()

        if (mitems[position].isLike)
            holder.ivAdapterLike.setImageResource(R.drawable.heart)
        else
            holder.ivAdapterLike.setImageResource(R.drawable.heart__1_)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mitems.size
    }

    inner class Holder(binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImageView = binding.imageView3
        val tvItemTitle = binding.textView2
        val tvPrice = binding.textView4
        val tvAddress = binding.textView3
        val tvItemChat = binding.textView6
        val tvItemLike = binding.textView6
        val ivAdapterLike = binding.imageView5

    }
}