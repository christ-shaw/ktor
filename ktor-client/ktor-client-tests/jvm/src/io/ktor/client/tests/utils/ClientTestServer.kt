/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.tests.utils

import io.ktor.application.*
import io.ktor.client.tests.utils.tests.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import java.util.concurrent.atomic.*

internal val counter = AtomicInteger(0)

internal fun Application.tests() {
    install(WebSockets) {
        maxFrameSize = 4 * 1024
    }

    authTestServer()
    encodingTestServer()
    serializationTestServer()
    cacheTestServer()
    loggingTestServer()
    contentTestServer()
    fullFormTest()
    redirectTest()
    featuresTest()
    webSockets()
    multiPartFormDataTest()
    headersTestServer()
    timeoutTest()
    cookiesTest()
    jsonTest()
    multithreadedTest()

    routing {
        post("/echo") {
            val response = call.receiveText()
            call.respond(response)
        }
        get("/bytes") {
            val size = call.request.queryParameters["size"]!!.toInt()
            call.respondBytes(makeArray(size))
        }
    }
}

internal suspend fun ApplicationCall.fail(text: String): Nothing {
    respondText(text, status = HttpStatusCode(400, text))
    error(text)
}
