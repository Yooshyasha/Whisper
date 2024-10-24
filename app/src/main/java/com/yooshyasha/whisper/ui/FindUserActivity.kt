package com.yooshyasha.whisper.ui

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import com.yooshyasha.whisper.R

class FindUserActivity : Activity() {

    private lateinit var inputNickname: EditText
    private lateinit var buttonFoundUser: Button

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.find_user_activity)

        inputNickname = findViewById(R.id.editTextInputUsername)
        buttonFoundUser = findViewById(R.id.sendButton)

    }

}