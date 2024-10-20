package com.yooshyasha.whisper.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class RequestProvider {

    private val client = OkHttpClient()

    suspend fun get(url: String, headers: Map<String, String> = emptyMap()): String? {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .apply {
                    headers.forEach { (key, value) -> addHeader(key, value) }
                }
                .build()

            client.newCall(request).execute().body?.string()
        }
    }
}