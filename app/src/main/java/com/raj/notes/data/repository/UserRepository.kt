package com.raj.notes.data.repository

import com.raj.notes.data.local.db.DatabaseService
import com.raj.notes.data.local.prefs.UserPreferences
import com.raj.notes.data.model.User
import com.raj.notes.data.remote.NetworkService
import com.raj.notes.data.remote.request.LoginRequest
import com.raj.notes.data.remote.request.SignUpRequest
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val userPreferences: UserPreferences
) {

    fun saveCurrentUser(user: User) {
        userPreferences.setUserId(user.id)
        userPreferences.setUserName(user.name)
        userPreferences.setUserEmail(user.email)
        userPreferences.setAccessToken(user.accessToken)
    }

    fun removeCurrentUser() {
        userPreferences.removeUserId()
        userPreferences.removeUserName()
        userPreferences.removeUserEmail()
        userPreferences.removeAccessToken()
    }

    fun getCurrentUser(): User? {

        val userId = userPreferences.getUserId()
        val userName = userPreferences.getUserName()
        val userEmail = userPreferences.getUserEmail()
        val accessToken = userPreferences.getAccessToken()

        return if (userId !== null && userName != null && userEmail != null && accessToken != null)
            User(userId, userName, userEmail, accessToken)
        else
            null
    }

    fun doUserLogin(email: String, password: String): Single<User> =
        networkService.doLoginCall(LoginRequest(email, password))
            .map {
                User(
                    it.userId,
                    it.userName,
                    it.userEmail,
                    it.accessToken
                )
            }

    fun doUserSignUp(email: String, password: String, name: String): Single<User> =
        networkService.doSignUpCall(SignUpRequest(email, password,name))
            .map {
                User(
                    it.userId,
                    it.userName,
                    it.userEmail,
                    it.accessToken
                )
            }
}