package com.example.narodi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.narodi.databinding.NarodItemBinding

class ItemNarodAdapter(val listener: Listener): RecyclerView.Adapter<ItemNarodAdapter.ItemNarodHolder>() {
    val itemNarodList = ArrayList<ItemNarod>()
    class ItemNarodHolder(item: View):RecyclerView.ViewHolder(item){
        val binding = NarodItemBinding.bind(item)

        fun bind(itemNarod: ItemNarod, listener: Listener) = with(binding){
            im.setImageResource(itemNarod.avatarUrl)
            tvTitle.text = itemNarod.nameN
            if (itemNarod.favorite == 0){
                imFav.setImageResource(R.drawable.favorite_inact)
            }
            else{
                imFav.setImageResource(R.drawable.favorite_act)
            }
            itemView.setOnClickListener {
                listener.onClick(itemNarod)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNarodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.narod_item, parent, false)
        return ItemNarodHolder(view)
    }

    override fun getItemCount(): Int {
        return itemNarodList.size
    }

    override fun onBindViewHolder(holder: ItemNarodHolder, position: Int) {
        holder.bind(itemNarodList[position], listener)
    }

    fun addItemNarod(itemNarod: ItemNarod){
        itemNarodList.add(itemNarod)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(itemNarod: ItemNarod)
    }
}