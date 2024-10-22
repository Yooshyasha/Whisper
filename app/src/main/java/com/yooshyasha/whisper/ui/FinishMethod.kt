package com.yooshyasha.whisper.ui

interface FinishMethod<T> {
    
    fun finishMethod(result: T?, success: Boolean)
    
}