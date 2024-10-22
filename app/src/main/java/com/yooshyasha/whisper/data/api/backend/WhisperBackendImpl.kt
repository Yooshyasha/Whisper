package com.yooshyasha.whisper.data.api.backend

import android.util.Log
import com.google.gson.Gson
import com.yooshyasha.whisper.data.api.makeRequest
import com.yooshyasha.whisper.data.model.ChatDTO
import com.yooshyasha.whisper.data.model.UserDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.UUID

const val BACKEND_URL = "http://localhost:8080"

class WhisperBackendImpl(
    private var token: String?
) : WhisperBackend {

    override fun registration(nickname: String): String? {
        val endpoint = "/auth/register"

        val observe = makeRequest("$BACKEND_URL$endpoint")
            .map { Gson().toJson(it) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        val request = observe.subscribe({

            token = it.toString()

        }, { Log.e("request", "", it) })

        return token
    }

    override fun getChats(token: String): List<ChatDTO> {
        getMe(token = token)

        // TODO доделать
        return emptyList()
    }

    override fun sendMessage(text: String, chatId: Long, token: String) {
        TODO("Not yet implemented")
    }

    override fun isAuth(token: String): Boolean {
        val endpoint = "/auth/isAuth"

        // TODO доделать
        return false
    }

    override fun getMe(token: String): UserDTO? {
        val endpoint = "/user/getMe"

        // TODO доделать
        return null
    }

    override fun getUser(userID: UUID): UserDTO? {
        val endpoint = "/user/getUser/$userID"

        // TODO доделать
        return null
    }
}