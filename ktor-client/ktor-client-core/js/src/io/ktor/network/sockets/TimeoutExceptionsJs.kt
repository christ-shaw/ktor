/*
 * Copyright 2014-2020 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.network.sockets

import io.ktor.utils.io.errors.*

/**
 * This exception is thrown in case connect timeout exceeded.
 */
actual class ConnectTimeoutException actual constructor(
    message: String, cause: Throwable?
) : IOException(message, cause)

/**
 * This exception is thrown in case socket timeout (read or write) exceeded.
 */
actual class SocketTimeoutException actual constructor(
    message: String, cause: Throwable?
) : IOException(message, cause)
