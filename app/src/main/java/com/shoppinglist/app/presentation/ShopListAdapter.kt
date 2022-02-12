package com.shoppinglist.app.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shoppinglist.app.R
import com.shoppinglist.app.databinding.ItemShopDisabledBinding
import com.shoppinglist.app.databinding.ItemShopEnabledBinding
import com.shoppinglist.app.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        private const val TYPE_DISABLED = 0
        private const val TYPE_ENABLED = 1

    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return shopList.size
    }


    private inner class ItemShopEnabled(private val itemEnabled: ItemShopEnabledBinding) :
        RecyclerView.ViewHolder(itemEnabled.root) {

        fun bind(shopItem: ShopItem) {
            itemEnabled.tvName.text = shopItem.name
            itemEnabled.tvCount.text = shopItem.count.toString()
        }

    }

    private inner class ItemShopDisabled(private val itemDisabled: ItemShopDisabledBinding) :
        RecyclerView.ViewHolder(itemDisabled.root) {
        fun bind(shopItem: ShopItem) {
            itemDisabled.tvName.text = shopItem.name
            itemDisabled.tvCount.text = shopItem.count.toString()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) TYPE_ENABLED else TYPE_DISABLED
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_ENABLED) {
            val view =
                ItemShopEnabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemShopEnabled(view)
        } else {
            val view =
                ItemShopDisabledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemShopDisabled(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == TYPE_ENABLED) {
            (viewHolder as ItemShopEnabled).bind(shopList[position])
        } else {
            (viewHolder as ItemShopDisabled).bind(shopList[position])
        }
        viewHolder.itemView.setOnLongClickListener {
            true
        }

    }


}