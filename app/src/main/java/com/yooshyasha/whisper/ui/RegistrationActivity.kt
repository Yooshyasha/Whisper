package com.yooshyasha.whisper.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.yooshyasha.whisper.R
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackendImpl
import com.yooshyasha.whisper.presentation.RegistrationViewModel

class RegistrationActivity : Activity() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var inputNickname: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        val tokenManager = TokenManager(this)
        val whisperBackend = WhisperBackendImpl(null)

        viewModel = RegistrationViewModel(tokenManager, whisperBackend)

        inputNickname = findViewById(R.id.input_nickname)
        registerButton = findViewById(R.id.button_registration)

        registerButton.setOnClickListener {
            val nickname = inputNickname.text.toString()

            if (nickname.isNotEmpty()) {
                val token = viewModel.registerUser(nickname)

                if (token != null) {
                    Toast.makeText(this, "Registration success", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a nickname", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
