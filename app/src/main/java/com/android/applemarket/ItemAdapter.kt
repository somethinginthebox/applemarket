package com.android.applemarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.ItemRecyclerViewBinding
import java.text.DecimalFormat


class ItemAdapter(val mItems: MutableList<MarketItem>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }
    interface ItemLongClick {
        fun onLongClick(view : View, position : Int)
    }


    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null


    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            //이부분이 xml 파일과 이름이 똑같아야 함
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            Log.d("view","viewholder")
            Log.d("view","$itemClick")
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        //
        holder.itemImage.setImageResource(mItems[position].aIcon)
        holder.itemTitle.text = mItems[position].aTitle
        holder.itemAddress.text = mItems[position].aAddress

        val price = mItems[position].aPrice
        holder.itemPrice.text = DecimalFormat("#,###").format(price)+"원"

        holder.itemLike.text = mItems[position].aLikeCount.toString() //??왜 alike가 아니지? 24:55
        holder.itemChat.text = mItems[position].aChatting.toString()

        if(mItems[position].aLike) holder.iconlike.setImageResource(R.drawable.icon_heart_f)
            else holder.iconlike.setImageResource(R.drawable.icon_heart_b)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }


    //아이템 xml 코드에 있는 id
    inner class Holder(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImage = binding.itemImage
        val itemTitle = binding.itemTitle
        val itemAddress = binding.itemAddress
        val itemPrice = binding.itemPrice
        val itemChat = binding.itemChatCount
        val iconlike = binding.itemLikeIcon
        val itemLike = binding.itemLikeCount

    }
}