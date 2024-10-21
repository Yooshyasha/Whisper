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

class ChatsActivity : Activity() {

    private lateinit var tokenManager: TokenManager
    private lateinit var userViewModel: UserViewModel
    private lateinit var whisperBackend: WhisperBackend

    lateinit var vList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tokenManager = TokenManager(this)
        whisperBackend = WhisperBackendImpl(null)
        userViewModel = UserViewModel(tokenManager, whisperBackend)

        vList = findViewById<LinearLayout>(R.id.linear_list_items)

        if (!userViewModel.isAuth()!!) {
            val i = Intent(this, RegistrationActivity::class.java)
        }

        drawChats()
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
}