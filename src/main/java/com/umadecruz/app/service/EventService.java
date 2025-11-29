package com.umadecruz.app.service;

import com.umadecruz.app.model.Event;
import com.umadecruz.app.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> carregaTodos(){
        return repository.findAll();
    }

    public List<Event> buscarPorData(LocalDate date){
        return repository.findByEventDate(date);
    }
    public Event salvar(Event event){
        return repository.save(event);
    }


}
