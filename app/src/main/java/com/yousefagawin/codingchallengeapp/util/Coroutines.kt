package com.yousefagawin.codingchallengeapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//we use object so we can directly call the functions
//objects in kotlin are similar to static in java
object Coroutines {

    //Unit in kotlin is equivalent to void in java
    //this function is taking another function as parameter & we execute that work() function inside
    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {

            work()
        }

}