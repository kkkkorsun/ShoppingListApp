package com.shoppinglist.app.domain

class GetShopListItem(private val shopListRepository: ShopListRepository) {
    fun getItem(shopItemId: Int): ShopItem {
        return shopListRepository.getItem(shopItemId)
    }
}