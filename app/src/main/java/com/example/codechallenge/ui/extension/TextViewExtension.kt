package com.example.codechallenge.ui.extension

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, textList: List<String>, intervalMs: Long) {
    this@typeWrite.text = ""

    lifecycleOwner.lifecycleScope.launch {
        textList.forEach { newText ->
            async {
                repeat(newText.length) {
                    delay(intervalMs)
                    this@typeWrite.text = newText.take(it + 1)
                }
            }.await()
        }
    }
}
