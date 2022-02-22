package com.shoppinglist.app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoppinglist.app.data.ShopListRepositoryImpl
import com.shoppinglist.app.domain.AddShopListItem
import com.shoppinglist.app.domain.EditShopListItem
import com.shoppinglist.app.domain.GetShopListItem
import com.shoppinglist.app.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListItem = GetShopListItem(repository)
    private val addShopListItem = AddShopListItem(repository)
    private val editShopListItem = EditShopListItem(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _goCloseActivity = MutableLiveData<Unit>()
    val goCloseActivity: LiveData<Unit>
        get() = _goCloseActivity

    fun editShopListItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopListItem.editItem(item)
                finishWork()
            }
        }
    }

    fun getShopListItem(shopItemId: Int) {
        val item = getShopListItem.getItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopListItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopListItem.addItem(shopItem)
            finishWork()
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true

        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count < 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _goCloseActivity.value = Unit
    }
}