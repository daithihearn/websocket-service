package ie.daithi.websockets.service

import ie.daithi.websockets.model.WsMessage
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class WebsocketService(
    private val messageSender: SimpMessagingTemplate,
    @Value("\${REDIS_TOPIC}") private val topic: String
) {

    fun send(wsMessage: WsMessage) {
        if (logger.isDebugEnabled) logger.debug("Sending message: $wsMessage")
        messageSender.convertAndSendToUser(wsMessage.recipient, topic, wsMessage.message)
    }

    companion object {
        private val logger = LogManager.getLogger(WebsocketService::class.java)
    }
}