package com.shoppinglist.app.data

import com.shoppinglist.app.domain.ShopItem
import com.shoppinglist.app.domain.ShopListRepository


object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementID = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("$i", i, true)
            addItem(item)
        }
    }

    override fun addItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementID++
        }
        shopList.add(shopItem)


    }

    override fun deleteItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editItem(shopItem: ShopItem) {
        val oldElement = getItem(shopItem.id)
        shopList.remove(oldElement)
        addItem(shopItem)
    }

    override fun getItem(shopItemId: Int): ShopItem {
        return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId is not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toMutableList()
    }

}