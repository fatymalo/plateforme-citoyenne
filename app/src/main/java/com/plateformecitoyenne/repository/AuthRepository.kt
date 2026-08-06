package com.plateformecitoyenne.repository

import com.plateformecitoyenne.data.FirebaseAuthManager

class AuthRepository {

    private val authManager = FirebaseAuthManager()

    suspend fun login(email: String, password: String) =
        authManager.login(email, password)

    suspend fun register(email: String, password: String) =
        authManager.register(email, password)

    fun logout() = authManager.logout()

    fun currentUser() = authManager.currentUser()
}