package com.devlooping.api.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.devlooping.api.entity.CommentSummary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<Long, List<WebSocketSession>> postSessions = new ConcurrentHashMap<>();

    public PostHandler() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Deshabilitar para usar ISO 8601
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long postId = extractPostId(session);
        postSessions.computeIfAbsent(postId, k -> new CopyOnWriteArrayList<>()).add(session);
        log.info("New connection: " + session.getId() + " for post: " + postId);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("New message: " + message.getPayload());
        log.info("From: " + session.getId());

        // Aquí puedes procesar el mensaje recibido si es necesario
        session.sendMessage(new TextMessage("Mensaje recibido en post: " + extractPostId(session)));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Exception occurred: {} on session: {}", exception.getMessage(), session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Long postId = extractPostId(session);
        List<WebSocketSession> sessions = postSessions.get(postId);
        if (sessions != null) {
            sessions.remove(session);
            if (sessions.isEmpty()) {
                postSessions.remove(postId);
            }
        }
        log.info("Connection closed: " + session.getId() + " for post: " + postId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendComment(CommentSummary comment) throws IOException {
        String message = objectMapper.writeValueAsString(comment);
        List<WebSocketSession> sessions = postSessions.get(comment.getPostId());
        if (sessions != null) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            }
        }
    }

    private Long extractPostId(WebSocketSession session) {
        String path = session.getUri().getPath();
        String[] parts = path.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }
}
