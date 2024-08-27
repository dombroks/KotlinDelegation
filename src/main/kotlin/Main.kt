package org.example

import kotlin.reflect.KProperty


sealed class MessageType {
    data object SMS : MessageType()
    data object Email : MessageType()
}

sealed class SendingResult() {
    data object Success : SendingResult()
    data object Failure : SendingResult()
}

class Message(
    val content: String,
    val type: MessageType
)

interface MessageSender {
    fun sendMessage(message: Message): SendingResult
}

class SmsSender : MessageSender {
    override fun sendMessage(message: Message): SendingResult {
        if (message.type == MessageType.SMS) {
            println("sent an SMS message with content: ${message.content}")
            return SendingResult.Success
        } else {
            println("Unknown message type")
            return SendingResult.Failure
        }

    }
}

class EmailSender : MessageSender {
    override fun sendMessage(message: Message): SendingResult {
        if (message.type == MessageType.Email) {
            println("sent an Email message with content: ${message.content}")
            return SendingResult.Success
        } else {
            println("Unknown message type")
            return SendingResult.Failure
        }
    }
}

class MessageCountDelegate {
    private var value: Int = 0

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
        value = newValue
    }
}

class MessageService(messageSender: MessageSender) : MessageSender by messageSender {
    var count by MessageCountDelegate()

    fun send(message: Message) {
        val result = sendMessage(message)
        if (result == SendingResult.Success)
            count += 1
    }
}

fun main() {
    val sms = Message(type = MessageType.SMS, content = "this is an SMS message")
    val email = Message(type = MessageType.Email, content = "this is an Email message")

    val emailSender = EmailSender()
    val smsSender = SmsSender()

    val emailService = MessageService(emailSender)
    val smsService = MessageService(smsSender)

    emailService.send(email)
    smsService.send(sms)

    println("------------------------")
    println(emailService.count.toString() + " email were sent.")
    println(smsService.count.toString() + " SMS were sent.")
}