package com.shoppinglist.app.domain

interface ShopListRepository {

    fun addItem(shopItem: ShopItem)

    fun deleteItem(shopItem: ShopItem)

    fun editItem(shopItem: ShopItem)

    fun getItem(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>

}