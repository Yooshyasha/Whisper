package com.yooshyasha.whisper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackend

class RegistrationViewModelFactory(
    private val tokenManager: TokenManager,
    private val whisperBackend: WhisperBackend
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(tokenManager, whisperBackend) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
