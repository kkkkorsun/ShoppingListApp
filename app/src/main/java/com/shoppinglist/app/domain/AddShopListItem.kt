package com.shoppinglist.app.domain

class AddShopListItem(private val shopListRepository: ShopListRepository) {

    fun addItem(shopItem: ShopItem) {
        shopListRepository.addItem(shopItem)
    }
}