package com.umadecruz.app.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class FcmService {

    @PostConstruct
    public void init() throws IOException {
        String json = System.getenv("FIREBASE_SERVICE_ACCOUNT");
        InputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(is)).build();
        if (FirebaseApp.getApps().isEmpty()) FirebaseApp.initializeApp(options);
    }

    public void send(String token, String title, String body) throws FirebaseMessagingException {
        Message msg = Message.builder().setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .build();
        FirebaseMessaging.getInstance().send(msg);
    }
}
