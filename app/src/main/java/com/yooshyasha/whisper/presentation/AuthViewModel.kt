package com.yooshyasha.whisper.presentation

import androidx.lifecycle.ViewModel
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackend

class AuthViewModel(
    private val tokenManager: TokenManager,
    private val whisperBackend: WhisperBackend?,
) : ViewModel() {

    fun registerUser(nickname: String) : String? {
        // Регистрация пользователя и сохранение токена
        val token = whisperBackend?.registration(nickname)

        if (token != null) tokenManager.saveToken(token)
        else return null

        return token
    }

    fun getToken() : String? {
        // Возвращает токен пользователя
        return tokenManager.getToken()
    }
}