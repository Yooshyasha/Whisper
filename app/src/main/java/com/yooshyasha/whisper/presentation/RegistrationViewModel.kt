package com.yooshyasha.whisper.presentation

import androidx.lifecycle.ViewModel
import com.yooshyasha.whisper.data.api.TokenManager
import com.yooshyasha.whisper.data.api.WhisperBackend

class RegistrationViewModel(
    private val tokenManager: TokenManager,
    private val whisperBackend: WhisperBackend,
) : ViewModel() {

    fun registerUser(nickname: String) : String {
        // Регистрация пользователя и сохранение токена
        val token = whisperBackend.registration(nickname)
        tokenManager.saveToken(token)
        return token
    }

    fun getToken() : String? {
        // Возвращает токен пользователя
        return tokenManager.getToken()
    }
}