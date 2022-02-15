package com.shoppinglist.app.presentation

import androidx.lifecycle.ViewModel
import com.shoppinglist.app.data.ShopListRepositoryImpl
import com.shoppinglist.app.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val getShopListItem = GetShopListItem(repository)
    private val deleteShopListItem = DeleteShopListItem(repository)
    private val editShopListItem = EditShopListItem(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopListItem.deleteItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopListItem.editItem(newItem)
    }

}