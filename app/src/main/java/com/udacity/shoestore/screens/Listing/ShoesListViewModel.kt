package com.udacity.shoestore.screens.Listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesListViewModel : ViewModel() {

    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList
    private var _shoeList = MutableLiveData<ArrayList<Shoe>>()

    init {
        _shoeList.value = ArrayList<Shoe>()
    }

    fun addShoe(newShoe: Shoe?) {
        if (newShoe != null) {
            _shoeList.value?.add(newShoe)
        }
    }


}