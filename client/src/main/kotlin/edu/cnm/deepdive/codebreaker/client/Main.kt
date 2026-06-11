package edu.cnm.deepdive.codebreaker.client

import edu.cnm.deepdive.codebreaker.client.dto.GameRequest
import edu.cnm.deepdive.codebreaker.client.service.CodebreakerService
import java.util.concurrent.CompletableFuture

fun main() {
    val service = CodebreakerService.instance
    val future = service.startGame(GameRequest("ABDCEF", 3))
    future.thenAccept {
        println(it)
    }
}