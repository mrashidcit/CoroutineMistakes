package com.rashidsaleem.coroutinemistakes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * Mistake 3 : Network call should be main safe.
 * 3rd party libraries like (Retrofit, Room) already do that under the hood.
 *
 * We need to apply thing if we are writing our network library or 3rd pary library
 */

suspend fun doNetworkCall(): Result<String> {
    val result = networkCall()
    return if (result == "Success") {
        Result.success(result)
    } else {
        Result.failure(Exception())
    }
}

suspend fun networkCall(): String {
    return withContext(Dispatchers.IO) {
        delay(3000L)
        if (Random.nextBoolean()) "Success" else "Error"
    }
}