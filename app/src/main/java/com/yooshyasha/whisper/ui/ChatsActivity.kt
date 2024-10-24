package com.yooshyasha.whisper.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yooshyasha.whisper.R
import com.yooshyasha.whisper.data.TokenManager
import com.yooshyasha.whisper.data.model.ChatDTO
import com.yooshyasha.whisper.presentation.UserViewModel
import com.yooshyasha.whisper.ui.adapter.RecyclerAdapter

class ChatsActivity : Activity(), FinishMethod<Boolean> {

    private lateinit var userViewModel: UserViewModel
    private lateinit var vList: RecyclerView
    private var chatsList = mutableListOf<ChatDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chats_activity)

        userViewModel = UserViewModel(TokenManager(this))

        userViewModel.isAuth(this)

        vList = findViewById(R.id.chatsList)

        findViewById<Button>(R.id.sendButton).setOnClickListener { bindSendMessageButton() }
    }

    override fun finish() {
        return
    }

    private fun drawChats() {
        userViewModel.getChats()?.let {
            chatsList = it.toMutableList()
            showRecycler(chatsList)
        }
    }

    private fun addChat(chatDTO: ChatDTO) {
        chatsList.add(chatDTO)
        vList.adapter?.notifyItemInserted(chatsList.size - 1)
    }

    private fun showRecycler(chatsList: List<ChatDTO>) {
        vList.adapter = RecyclerAdapter(chatsList)
        vList.layoutManager = LinearLayoutManager(this)
    }

    override fun finishMethod(result: Boolean?, success: Boolean) {
        if (result == false) {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }

        drawChats()
    }

    @SuppressLint("InflateParams")
    private fun bindSendMessageButton() {
        val i = Intent(this, FindUserActivity::class.java)
        startActivity(i)
    }
}