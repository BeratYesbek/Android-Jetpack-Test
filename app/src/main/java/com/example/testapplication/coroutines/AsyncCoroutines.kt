package com.example.testapplication.coroutines

import com.example.testapplication.models.User
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val downloadedUser = async {
            downloadUser()
        }

        val downloadedAge = async {
            downloadUserAge()
        }

        val user = downloadedUser.await()
        val age = downloadedAge.await()
        println(user.email)
        println(age)
    }
}

suspend fun downloadUserAge() : Int {
    delay(2000)
    return 21;
}

suspend fun downloadUser() : User {
    delay(4000)
    return User(1,"Berat","beratyesbek@gmail.com","male","active")
}