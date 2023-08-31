package com.example.applemarket

import android.icu.text.DecimalFormat
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class itemAdapter(private val mitems: MutableList<SaleItem>) :
    RecyclerView.Adapter<itemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: itemAdapter.Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        holder.itemImageView.setImageResource(mitems[position].Image)
        holder.tvItemTitle.text = mitems[position].ItemTitle
        holder.tvAddress.text = mitems[position].Address

        val price = mitems[position].Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price) + "원"

        holder.tvItemChat.text = mitems[Position].ChatCnt.toString()
        holder.tvItemLike.text = mitems[Position].InterestCnt.toString()

        if (mitems[Position].isLike)
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

    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImageView = binding.iconItem
        val tvItemTitle = binding.tvItemTitle
        val tvAddress = binding.tvAddress
        val tvItemChat = binding.tvItemChat
        val tvItemLike = binding.tvLikecnt
        val ivAdapterLike = binding.ivAdapterLike
    }
}

//
//    inner class Holder(private val binding: ActivityDetailBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        val imageView3: ImageView = binding.imageView3
//        val textView2: TextView = binding.textView2
//        val textView3: TextView = binding.textView3
//        val textView4: TextView = binding.textView4
//        val textView5: TextView = binding.textView5
//        val textView6: TextView = binding.textView6
//
//        init {
//            // 아이템 뷰 클릭 이벤트 처리
//            itemView.setOnClickListener {
//                itemClick?.onClick(it, adapterPosition)
//            }
//        }
//    }package com.example.applemarket
//
//    import android.view.LayoutInflater
//    import android.view.ViewGroup
//    import androidx.recyclerview.widget.RecyclerView
//    import com.example.applemarket.databinding.ItemBinding
//    import java.text.DecimalFormat
//
//    class itemAdapter(private val items: MutableList<itemParcel>) :
//        RecyclerView.Adapter<itemAdapter.Holder>() {
//
//        interface ItemClick {
//            fun onClick(view: android.view.View, position: Int)
//        }
//
//        var itemClick: ItemClick? = null
//
//        override fun getItemCount(): Int {
//            return items.size
//        }
//
//        inner class Holder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
//
//            val imageView3 = binding.imageView3
//            val textView2 = binding.textView2
//            val textView3 = binding.textView3
//            val textView4 = binding.textView4
//            val textView5 = binding.textView5
//            val textView6 = binding.textView6
//
//            init {
//                itemView.setOnClickListener {
//                    itemClick?.onClick(it, adapterPosition)
//                }
//            }
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//            val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//            return Holder(binding)
//        }
//
//        override fun onBindViewHolder(holder: Holder, position: Int) {
//            val currentItem = items[position]
//
//            holder.imageView3.setImageResource(currentItem.Image)
//            holder.textView2.text = currentItem.ItemTitle
//            holder.textView3.text = currentItem.Address
//
//            val price = currentItem.Price
//            val priceFormatted = DecimalFormat("#,###").format(price)
//            holder.textView4.text = "$priceFormatted 원"
//
//            holder.textView5.text = currentItem.ChatCnt.toString()
//            holder.textView6.text = currentItem.InterestCnt.toString()
//
//            // Update the heart icon based on the 'isLike' value
//            val heartDrawableId = if (currentItem.isLike) R.drawable.heart else R.drawable.heart__1_
//            holder.textView5.setCompoundDrawablesWithIntrinsicBounds(heartDrawableId, 0, 0, 0)
//        }
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemAdapter.Holder {
//
//        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return Holder(binding)
//    }
//
//    override fun onBindViewHolder(holder: itemAdapter.Holder, position: Int) {
//        // onBindViewHolder 내부의 아이템 뷰 설정 코드를 그대로 유지
//        holder.imageView3.setImageResource(mitems[position].Image)
//        holder.textView2.text = mitems[position].ItemTitle
//        holder.textView3.text = mitems[position].Address
//
//        val price = mitems[position].Price
//        holder.textView4.text = DecimalFormat("#,###").format(price) + "원"
//
//        holder.textView5.text = mitems[position].ChatCnt.toString()
//        holder.textView6.text = mitems[position].InterestCnt.toString()
//
////        if (mitems[position].isLike)
////            holder.imageView5.setImageResource(R.drawable.heart__1_)
////        else
////            holder.imageView5.setImageResource(R.drawable.heart__1_)
//    }
//
//

