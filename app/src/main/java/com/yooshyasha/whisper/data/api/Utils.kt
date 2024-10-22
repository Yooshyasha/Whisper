package com.yooshyasha.whisper.data.api

import android.util.Log
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL


fun makeRequest(
    url: String,
    requestMethod: String = "GET",
    headers: Map<String, String> = emptyMap(),
    body: Map<String, String> = emptyMap()
) = Observable.create<String> { it ->
    val urlConnection = URL(url).openConnection() as HttpURLConnection
    urlConnection.requestMethod = requestMethod

    val jsonBody = Gson().toJson(body)

    try {

        for (header in headers) {
            urlConnection.setRequestProperty(header.key, header.value)
        }

        urlConnection.outputStream.use { os: OutputStream ->
            val input = jsonBody.toByteArray()
            os.write(input, 0, input.size)
        }

        urlConnection.connect()

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
            Log.i("request", "${urlConnection.responseMessage} - ${urlConnection.responseCode}")
            it.onError(RuntimeException(urlConnection.responseMessage))
        } else {
            it.onNext(urlConnection.inputStream.bufferedReader().readText())
        }
    } finally {
        urlConnection.disconnect()
    }
}