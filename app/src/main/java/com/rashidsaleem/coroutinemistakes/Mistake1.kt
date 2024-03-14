package com.rashidsaleem.coroutinemistakes

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * Mistake 1 : Doing everything sequentially which doesn't need to be sequentially
 */

private const val TAG = "Mistake1"

// ******* Before ********* //

//suspend fun getUserFirstNames(userIds: List<String>): List<String> {
//    val firstNames = mutableListOf<String>()
//    for (id in userIds) {
//        firstNames.add(getFirstName(id))
//    }
//    return firstNames
//}

// ******** After ********* //

suspend fun getUserFirstNames(userIds: List<String>): List<String> {
    val firstNames = mutableListOf<Deferred<String>>()
    coroutineScope {
        for (id in userIds) {
            val firstName = async { getFirstName(id) }
            firstNames.add(firstName)
        }

    }
    return firstNames.awaitAll()
}

private suspend fun getFirstName(userId: String): String {
    Log.d(TAG, "getFirstName: userId = $userId")
    delay(1000L)
    return "First Name"
}