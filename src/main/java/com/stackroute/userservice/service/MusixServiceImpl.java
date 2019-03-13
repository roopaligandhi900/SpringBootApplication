package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.Musix;
import com.stackroute.userservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.userservice.exceptions.TrackNotFoundException;
import com.stackroute.userservice.repository.MusixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MusixServiceImpl implements MusixService, ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {


    @Value("${musix.1.name:default}")
    String name1;
    @Value("${musix.1.rating:default}")
    int rating1;
    @Value("${musix.1.comments:default}")
    String comments1;
    @Value("${musix.2.name:default}")
    String name2;
    @Value("${musix.2.rating:default}")
    int rating2;
    @Value("${musix.2.comments:default}")
    String comments2;
    private MusixRepository musixRepository;


    public MusixServiceImpl(MusixRepository userRepository) {

        this.musixRepository = userRepository;
    }

    @Autowired
    public MusixRepository getUserRepository() {

        return musixRepository;
    }

    public void setUserRepository(MusixRepository userRepository) {

        this.musixRepository = userRepository;
    }

    public Musix saveMusix(Musix musix) throws TrackAlreadyExistsException {

        if (musixRepository.existsById(musix.getId())) {

            throw new TrackAlreadyExistsException("Track already exists with id  : " + musix.getId());
        }

        Musix savedMusix = musixRepository.save(musix);

        return savedMusix;
    }

    public List<Musix> getMusix() {

        return (List<Musix>) musixRepository.findAll();

    }

    public Musix getById(int id) throws TrackNotFoundException {

        Optional<Musix> user_id = musixRepository.findById(id);


        if (!user_id.isPresent())

            throw new TrackNotFoundException("Record not found");

        return user_id.get();


    }

    public void deleteById(int id) {
        musixRepository.deleteById(id);

    }

    public boolean updateById(Musix musix, int id) {
        Optional<Musix> userOptional = musixRepository.findById(id);

        if (!userOptional.isPresent())
            return false;


        musix.setId(id);

        musixRepository.save(musix);
        return true;
    }

    public List<Musix> getBYName(String name) {
        List<Musix> user_id = musixRepository.findTitleByName(name);

        return user_id;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        musixRepository.save(new Musix(1, name1, rating1, comments1));
        musixRepository.save(new Musix(2, name2, rating2, comments2));
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
