package com.yooshyasha.whisper.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yooshyasha.whisper.data.api.TokenManager
import com.yooshyasha.whisper.data.api.WhisperBackend

class RegistrationViewModelFactory(
    private val tokenManager: TokenManager,
    private val whisperBackend: WhisperBackend
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(tokenManager, whisperBackend) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
