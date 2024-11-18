package com.example.viewmodelscopedemo.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(8000)
        return listOf(
            User(1, "Lucas"),
            User(2, "Veron"),
            User(3, "Kelvin"),
            User(4, "Vivian"),
            User(5, "Ashley")
        )
    }
}