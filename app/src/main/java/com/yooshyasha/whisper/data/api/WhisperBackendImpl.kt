package com.yooshyasha.whisper.data.api

import com.yooshyasha.whisper.data.model.ChatDTO
import com.yooshyasha.whisper.data.model.UserDTO
import java.util.UUID

const val BACKEND_URL = "http://whisper.sytes.net"

class WhisperBackendImpl(
    private val token: String?
) : WhisperBackend{

    override fun registration(nickname: String): String? {
        val endpoint = "/auth/register"

        // TODO доделать
        return null
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