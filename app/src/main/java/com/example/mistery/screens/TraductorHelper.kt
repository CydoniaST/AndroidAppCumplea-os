package com.example.mistery.screens

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

object TranslatorHelper {

    private val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.RUSSIAN)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()

    private val translator = Translation.getClient(options)

    fun translate(
        text: String,
        onResult: (String) -> Unit,
        onError: (Exception) -> Unit
    ) {

        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                translator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        onResult(translatedText)
                    }
                    .addOnFailureListener { e -> onError(e) }
            }
            .addOnFailureListener { e -> onError(e) }
    }
}
