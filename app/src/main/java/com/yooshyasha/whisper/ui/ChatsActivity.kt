package com.yooshyasha.whisper.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.yooshyasha.whisper.R
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.api.backend.WhisperBackend
import com.yooshyasha.whisper.data.api.backend.WhisperBackendImpl
import com.yooshyasha.whisper.data.model.ChatDTO
import com.yooshyasha.whisper.presentation.UserViewModel
import java.util.zip.Inflater

class ChatsActivity : Activity(), FinishMethod<Boolean> {

    private lateinit var userViewModel: UserViewModel

    private lateinit var vList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chats_activity)

        userViewModel = UserViewModel(TokenManager(this))

        vList = findViewById(R.id.linear_list_items)

        userViewModel.isAuth(this)
    }

    private fun drawChats() {

        val inflater = layoutInflater

        userViewModel.getChats()?.forEach { chat ->
            drawChat(chat, inflater)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun drawChat(chat : ChatDTO, inflater: LayoutInflater) {
        val view = inflater.inflate(R.layout.list_items, vList)
        val vTitle = view.findViewById<TextView>(R.id.item_title)
        vTitle.text = chat.chatId.toString()
        vList.addView(view)
    }

    override fun finishMethod(result: Boolean?, success: Boolean) {
        if (result == false) {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }

        drawChats()
    }
}