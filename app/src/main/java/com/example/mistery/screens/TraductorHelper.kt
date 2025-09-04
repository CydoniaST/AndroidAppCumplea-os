package com.example.mistery.screens

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive


object TranslatorHelper {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    fun translate(
        text: String,
        onResult: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: JsonArray = client.get(
                    "https://translate.googleapis.com/translate_a/single"
                ) {
                    url {
                        parameters.append("client", "gtx")
                        parameters.append("sl", "auto") // idioma origen: automatico
                        parameters.append("tl", "es")  // idioma destino: español
                        parameters.append("dt", "t")
                        parameters.append("q", text)
                    }
                }.body()


                val translated = response[0]
                    .jsonArray[0]
                    .jsonArray[0]
                    .jsonPrimitive.content

                onResult(translated)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}

