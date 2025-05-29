package com.rururi.easyprompt.utils

import android.util.Log

const val TAG = "ruru"

fun log(
    tag: String = TAG,
    message: String,
) {
    val methodName = Thread.currentThread().stackTrace[4].methodName
    val logMessage = "[$methodName] $message"
    Log.d(tag, logMessage)
}