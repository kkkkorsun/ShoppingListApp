package com.shoppinglist.app.domain

class EditShopListItem(private val shopListRepository: ShopListRepository) {

    fun editItem(shopItem: ShopItem) {
        shopListRepository.editItem(shopItem)
    }
}