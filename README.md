These are the 5 Fatal Coroutine mistaks that we should aware of when using coroutines.


Mistake #1 : Doing everything sequentially which doesn't need to be sequentially.
        - use these in your suspend functions to avoid that issue 
             i. coroutineScope { }
            ii. async { }

Mistake #2 : If in coroutineScope we are doing so much complex task or runny for loop that it may possible that job is canclled from
outside the scope of that coroutineScope but coroutine won't aware of that for quite some time.
        - use <b>ensureActive()</b> method to avoid that issue

Mistake #3 : Network call is not main safe. So If you are using native android class for network operations or writing your own networking library. Then you need 
to make sure that network calls should be done on IO dispatcher.
        - use <b>withContext(Dispations.IO) { }</b> in your network call suspend function
        Although if you are using any 3rd Pary library like Retrofit or Room then they already do that thing under the hood.

Mistake #4 : Forwarding CancellationException to ParentScope. If any CancellationException occure in suspend function that that should be forward to the Parent scope.
        - example
```kotlin
try {
        println("The answer is ${10 / 0}")
} catch (ex: Exception) {
        if (ex is CancellationException) {
          throw ex
        }
        println("Oops, that didn't work")
}
```

Mistake #5 : Exposing ViewModel suspend functions to the UI.
          Well ViewModel shouldn't expose the suspend function to the UI. Reason because <u>Activity lifecycle</u> will be destryed when activity will be destroyed.
e.g when we Roteate the device or enable the Dark/Light Mode.
      
