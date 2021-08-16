package com.dedan.ekitabu.data

import android.util.Log
import com.dedan.ekitabu.data.model.LoggedInUser
import com.dedan.ekitabu.network.Requests
import com.dedan.ekitabu.network.UserServices
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.security.auth.callback.Callback

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor (private val userServices: UserServices) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        try {

            userServices.login(Requests(username, password)).enqueue(
                object : Callback, retrofit2.Callback<Any> {
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {

                        Log.e("login success", response.code().toString())
                        //setLoggedInUser(result.data)
                    }

                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        Log.e("login failed", t.localizedMessage.toString())
                    }

                }
            )

            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}