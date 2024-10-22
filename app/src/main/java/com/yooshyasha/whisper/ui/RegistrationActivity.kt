package com.yooshyasha.whisper.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.yooshyasha.whisper.R
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackendImpl
import com.yooshyasha.whisper.presentation.AuthViewModel

class RegistrationActivity : Activity(), FinishMethod<String> {

    private lateinit var viewModel: AuthViewModel
    private lateinit var inputNickname: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        val tokenManager = TokenManager(this)
        val whisperBackend = WhisperBackendImpl(null)

        viewModel = AuthViewModel(tokenManager, whisperBackend)

        inputNickname = findViewById(R.id.input_nickname)
        registerButton = findViewById(R.id.button_registration)

        registerButton.setOnClickListener {
            val nickname = inputNickname.text.toString()

            if (nickname.isNotEmpty()) {
                viewModel.registerUser(nickname, this)
            } else {
                Toast.makeText(this, "Please enter a nickname", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun finish() {
        return
    }

    override fun finishMethod(result: String?, success: Boolean) {
        if (result != null) {
            Toast.makeText(this, "Registration success", Toast.LENGTH_SHORT).show()
            finishRegistration()
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun finishRegistration() {
        val i = Intent(this, ChatsActivity::class.java)
        startActivity(i)
    }
}
