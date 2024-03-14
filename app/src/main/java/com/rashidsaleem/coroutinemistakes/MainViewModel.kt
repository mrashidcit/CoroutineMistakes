package com.rashidsaleem.coroutinemistakes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val TAG = "MainViewModel"
    private val _scope = viewModelScope

    init {

        _scope.launch {

            // Mistake 1 -------------
//            val userIds = listOf("1", "2", "3", "4", "5", "6", "7", "8")
//
//            val result = getUserFirstNames(userIds)
//            Log.d(TAG, "result = ${result.joinToString()} ")

            // Mistake 2 -------------
            doSomething()
        }

    }

    /**
     * Mistake 5 : ViewModel suspend functions shouldn't  be exposed to the UI.
     *
     */

    // ********** Before *********** //
    /**
     *
    suspend fun postValueToApi() {
        delay(1000L)
    }
     */

    // ********** After *********** //
    fun postValueToApi() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000L)
        }
    }



}