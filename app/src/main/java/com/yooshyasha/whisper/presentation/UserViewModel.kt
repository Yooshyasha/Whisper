package com.yooshyasha.whisper.presentation

import androidx.lifecycle.ViewModel
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackend
import com.yooshyasha.whisper.data.model.ChatDTO
import com.yooshyasha.whisper.data.model.UserDTO
import java.util.UUID

class UserViewModel(
    private val tokenManager: TokenManager,
    private val whisperBackend: WhisperBackend,
) : ViewModel() {

    fun getMe() : UserDTO? {
        return tokenManager.getToken()?.let { whisperBackend.getMe(it) }
    }

    fun getUser(userUUID: UUID) : UserDTO? {
        return whisperBackend.getUser(userUUID)
    }

    fun getChats() : List<ChatDTO>? {
        return tokenManager.getToken()?.let { whisperBackend.getChats(it) }
    }

    fun sendMessage(text: String, chatId: Long) {
        tokenManager.getToken()?.let { whisperBackend.sendMessage(text, chatId, it) }
    }

    fun isAuth() : Boolean? {
        return tokenManager.getToken()?.let { whisperBackend.isAuth(it) }
    }
}