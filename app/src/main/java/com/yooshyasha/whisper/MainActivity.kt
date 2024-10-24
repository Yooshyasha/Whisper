package com.yooshyasha.whisper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.presentation.UserViewModel
import com.yooshyasha.whisper.ui.ChatsActivity
import com.yooshyasha.whisper.ui.FinishMethod
import com.yooshyasha.whisper.ui.RegistrationActivity

class MainActivity : AppCompatActivity(), FinishMethod<Boolean> {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        userViewModel = UserViewModel(TokenManager(this))
        val result = userViewModel.isAuth(this)

        if (result == null) {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }
    }

    override fun finishMethod(result: Boolean?, success: Boolean) {
        if (result == false) {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        } else {
            val i = Intent(this, ChatsActivity::class.java)
            startActivity(i)
        }
    }
}