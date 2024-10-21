package com.yooshyasha.whisper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackend
import com.yooshyasha.whisper.data.api.backend.WhisperBackendImpl
import com.yooshyasha.whisper.presentation.AuthViewModel
import com.yooshyasha.whisper.ui.ChatsActivity
import com.yooshyasha.whisper.ui.RegistrationActivity

class MainActivity : AppCompatActivity() {
    private lateinit var registrationViewModel: AuthViewModel
    private lateinit var whisperBackend: WhisperBackend

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        registrationViewModel = AuthViewModel(TokenManager(this), null)

        val token = registrationViewModel.getToken()
        whisperBackend = WhisperBackendImpl(token)

        registrationViewModel = AuthViewModel(TokenManager(this), whisperBackend)

        if ((token == null) || !whisperBackend.isAuth(token)) {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        } else {
            val i = Intent(this, ChatsActivity::class.java)
            startActivity(i)
        }
    }

}