package com.shoppinglist.app.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppinglist.app.data.ShopListRepositoryImpl
import com.shoppinglist.app.domain.DeleteShopListItem
import com.shoppinglist.app.domain.EditShopListItem
import com.shoppinglist.app.domain.GetShopListUseCase
import com.shoppinglist.app.domain.ShopItem

class MainViewModel() : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopListItem = DeleteShopListItem(repository)
    private val editShopListItem = EditShopListItem(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopListItem.deleteItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = shopItem.enabled)
        editShopListItem.editItem(shopItem)
        getShopList()

    }

}