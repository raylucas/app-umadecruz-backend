package com.umadecruz.app.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
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
                String messageId = FirebaseMessaging.getInstance().send(message);
                log.info("Push enviado com sucesso | token={} | messageId={}", token, messageId);
            } catch (FirebaseMessagingException e) {
                if (e.getMessagingErrorCode().name().equals("SENDER_ID_MISMATCH")) {
                    log.warn(
                            "Token inválido (SenderId mismatch) - possivelmente do Expo Go. Ignorando este token. | token={}",
                            token
                    );
                } else {
                    log.error(
                            "Erro ao enviar push | token={} | errorCode={} | message={}",
                            token,
                            e.getMessagingErrorCode(),
                            e.getMessage(),
                            e
                    );
                }
            } catch (Exception e) {
                log.error("Erro inesperado ao enviar push | token={}", token, e);
            }
        });
    }

}