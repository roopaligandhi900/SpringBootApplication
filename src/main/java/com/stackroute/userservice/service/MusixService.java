package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.Musix;
import com.stackroute.userservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.userservice.exceptions.TrackNotFoundException;

import java.util.List;

public interface MusixService {

    public Musix saveMusix(Musix musix) throws TrackAlreadyExistsException;

    public List<Musix> getMusix();

    public Musix getById(int id) throws TrackNotFoundException;

    public void deleteById(int id);

    public boolean updateById(Musix musix, int id);

    public List<Musix> getBYName(String name);
}
