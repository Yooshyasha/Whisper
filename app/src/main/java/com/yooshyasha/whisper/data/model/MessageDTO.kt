package com.yooshyasha.whisper.data.model

data class MessageDTO (
    val fromUser: UserDTO,
    val chat: ChatDTO,
    val text: String,
)