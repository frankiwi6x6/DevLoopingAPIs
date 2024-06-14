package com.devlooping.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.devlooping.api.websocket.CommentHandler;
import com.devlooping.api.websocket.PostHandler;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private PostHandler postHandler;

    @Autowired
    private CommentHandler commentHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(postHandler, "/ws/posts").setAllowedOrigins("*");
        registry.addHandler(commentHandler, "/ws/posts/{postId}").setAllowedOrigins("*");
    }
}
