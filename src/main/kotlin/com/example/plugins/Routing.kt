package com.example.plugins

import com.mailgun.api.v3.MailgunMessagesApi
import com.mailgun.client.MailgunClient
import com.mailgun.model.message.Message
import com.mailgun.util.EmailUtil
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        post("/sub") {
            val mailgunMessagesApi = MailgunClient.config("c1f1b6d3211afc32dde9a24d9cdc138d-324e0bb2-a2cc8358")
                .createApi(MailgunMessagesApi::class.java)

            val message: Message = Message.builder()
                .from("admin@m.kotlin.academy")
                .to(EmailUtil.nameWithEmail("recca", "flamerecca711@gmail.com"))
                .subject("From Ktor")
                .text("Hello world from ktor.")
                .build()

            val messageResponse = mailgunMessagesApi.sendMessage("m.kotlin.academy", message)
            call.respondText("Hello World!")
        }
    }
}
