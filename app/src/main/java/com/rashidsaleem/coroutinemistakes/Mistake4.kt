package com.rashidsaleem.coroutinemistakes

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

/**
 * Mistake 4 : Forwarding exception to parent coroutine scope
 */

// ********** Before *********** //

/**
 *
suspend fun riskyTask() {
    try {
        delay(3000L)
        println("The answer is ${10 / 0}")
    } catch (ex: Exception) {
        println("Oops, that didn't work")
    }
}

 */


// ********** After *********** //

suspend fun riskyTask() {
    try {
        delay(3000L)
        println("The answer is ${10 / 0}")
    } catch (ex: Exception) {
        if (ex is CancellationException) {
            throw ex
        }
        println("Oops, that didn't work")
    }
}