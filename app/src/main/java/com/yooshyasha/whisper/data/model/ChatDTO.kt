package com.yooshyasha.whisper.data.model

data class ChatDTO (
    val chatId: Long,
    val messages: List<MessageDTO>,
)