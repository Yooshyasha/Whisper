package com.yooshyasha.whisper.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.yooshyasha.whisper.R
import com.yooshyasha.whisper.data.api.TokenManager
import com.yooshyasha.whisper.data.api.WhisperBackendImpl
import com.yooshyasha.whisper.presentation.RegistrationViewModel
import com.yooshyasha.whisper.presentation.RegistrationViewModelFactory

class RegistrationActivity : AppCompatActivity() {

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var inputNickname: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        viewModel = ViewModelProvider(this,
                RegistrationViewModelFactory(TokenManager(this),
                WhisperBackendImpl(null)))
                .get(RegistrationViewModel::class.java)

        inputNickname = findViewById(R.id.input_nickname)
        registerButton = findViewById(R.id.button_registration)

        registerButton.setOnClickListener {
            val nickname = inputNickname.text.toString()

            if (nickname.isNotEmpty()) {
                viewModel.registerUser(nickname)

                val token = viewModel.getToken()
                if (token != null) {
                    // Дальнейшие действия с токеном
                } else {
                    // Обработка случая, когда токен не получен
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Обработка случая, когда никнейм пустой
                Toast.makeText(this, "Please enter a nickname", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
