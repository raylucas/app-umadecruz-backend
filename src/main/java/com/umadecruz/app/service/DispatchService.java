package com.umadecruz.app.service;

import com.umadecruz.app.model.DeviceToken;
import com.umadecruz.app.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class DispatchService {
    @Autowired
    private EventService eventService;

    @Autowired
    private DeviceTokenService deviceTokenService;

    @Autowired
    private FcmService fcm;

    @Value("${app.timezone:America/Sao_Paulo}") String tz;

    // cron read from property
    @Scheduled(cron = "0 50 7 * * *")
    public void dailyDispatch() {
        LocalDate today = LocalDate.now(ZoneId.of(tz));
        List<Event> events = eventService.buscarPorData(today);
        if (events.isEmpty()) return;
        List<DeviceToken> tokens = deviceTokenService.carregarTodos();
        for (DeviceToken token : tokens) {
            for (Event e : events) {
                try { fcm.send(token.getToken(), "Lembrete: " + e.getTitle(), e.getDescription()); }
                catch (Exception ex) { /* log */ }
            }
        }
    }
}
