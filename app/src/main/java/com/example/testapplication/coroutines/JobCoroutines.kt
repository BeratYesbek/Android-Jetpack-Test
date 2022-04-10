package com.example.testapplication.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
 runBlocking {
     val job = launch {
         delay(2000)
         println("job is working...")
     }

     job.invokeOnCompletion {
         println("job has been completed")
     }
 }
}