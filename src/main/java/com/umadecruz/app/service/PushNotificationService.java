package com.umadecruz.app.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PushNotificationService {

    public void enviarParaTokens(List<String> tokens, String titulo, String corpo) {

        tokens.forEach(token -> {
            var message = Message.builder()
                    .setToken(token)
                    .setNotification(
                            Notification.builder()
                                    .setTitle(titulo)
                                    .setBody(corpo)
                                    .build()
                    )
                    .build();

            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (Exception e) {
                log.error("Erro de envio ao token: {} " + e.getMessage(), token);
            }
        });
    }

}