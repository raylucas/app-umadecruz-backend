package com.umadecruz.app.controller;

import com.umadecruz.app.model.Event;
import com.umadecruz.app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> list(@RequestParam(required=false) String date) {
        if (date==null) return eventService.carregaTodos();
        return eventService.buscarPorData(LocalDate.parse(date));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Event create(@RequestBody Event e) {
        return eventService.salvar(e);
    }
}

