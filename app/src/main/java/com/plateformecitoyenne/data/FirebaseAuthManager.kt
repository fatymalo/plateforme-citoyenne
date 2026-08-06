package com.plateformecitoyenne.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthManager {

    private val auth = FirebaseAuth.getInstance()

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.signInWithEmailAndPassword(email, password).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    suspend fun register(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.createUserWithEmailAndPassword(email, password).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUser() = auth.currentUser
}