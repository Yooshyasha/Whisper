package com.yooshyasha.whisper.data.api.backend

import com.yooshyasha.whisper.data.model.ChatDTO
import com.yooshyasha.whisper.data.model.UserDTO
import com.yooshyasha.whisper.ui.FinishMethod
import java.util.UUID

interface WhisperBackend {

    fun registration(nickname: String, context: FinishMethod<String>) : String? // Обращение на бэкэнд с целью регистрации. Возвращает токен

    fun isAuth(token: String) : Boolean // Проверяем авторизацию токена

    fun getMe(token: String) : UserDTO? // Получить "себя"

    fun getUser(userID: UUID) : UserDTO? // Получить пользователя по UUID

    fun getChats(token: String) : List<ChatDTO> // Получаем чаты пользователя

    fun sendMessage(text: String, chatId: Long, token: String) // Отправляем сообщение в чат

}