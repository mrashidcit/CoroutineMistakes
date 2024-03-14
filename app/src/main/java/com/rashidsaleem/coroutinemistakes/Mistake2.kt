package com.rashidsaleem.coroutinemistakes

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

private const val TAG = "Mistake2"

/**
 * Mistake 2 : If in coroutine scope we are doing to much complex task. Then If we
 * cancel the coroutine job then sometimes coroutine won't be able to aware of that
 * cancellation and stuck in the loop or something for a quite time which leads to
 * logical errors which are very difficult to reproduce.
 */


// ****** Before ********* //
/**
 *
suspend fun doSomething() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        var random = Random.nextInt(100_000)
        while (random != 50000) {
            Log.d(TAG, "doSomething: $random")
            random = Random.nextInt(100_000)
        }
    }
    delay(50L)
    Log.d(TAG, "doSomething: job.cancel")
    job.cancel()
}
 */

// ******** After ******** //
suspend fun doSomething() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        var random = Random.nextInt(100_000)
        while (random != 50000) {
            ensureActive()
            Log.d(TAG, "doSomething: $random ... isActive = $isActive")
            random = Random.nextInt(100_000)
        }
    }
    delay(50L)
    Log.d(TAG, "doSomething: job.cancel")
    job.cancel()
}