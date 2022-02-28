package com.shoppinglist.app.presentation

import androidx.lifecycle.ViewModel
import com.shoppinglist.app.data.ShopListRepositoryImpl
import com.shoppinglist.app.domain.DeleteShopListItem
import com.shoppinglist.app.domain.EditShopListItem
import com.shoppinglist.app.domain.GetShopListUseCase
import com.shoppinglist.app.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopListItem(repository)
    private val editShopItemUseCase = EditShopListItem(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editItem(newItem)
    }

}