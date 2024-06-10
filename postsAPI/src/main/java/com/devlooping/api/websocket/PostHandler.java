package com.devlooping.api.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.devlooping.api.entity.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper;

    private final List<WebSocketSession> sessions = new ArrayList<>();

    public PostHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("New connection: " + session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("New message: " + message.getPayload());
        log.info("From: " + session.getId());

        // Aquí puedes procesar el mensaje recibido si es necesario
        // Por ahora, solo envía un mensaje de confirmación
        session.sendMessage(new TextMessage("Iniciado el proceso de creación de un post: " + session.getId()));
        Thread.sleep(1000);
        session.sendMessage(new TextMessage("Post creado con éxito: " + session.getId()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Exception occurred: {} on session: {}", exception.getMessage(), session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session);
        log.info("Connection closed: " + session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendComment(Comment comment) throws IOException {
        String message = objectMapper.writeValueAsString(comment);
        sendMessageToAll(message);
    }

    private void sendMessageToAll(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
