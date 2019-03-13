package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.Musix;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MusixRepository extends CrudRepository <Musix, Integer> {

    @Query(
            value = "SELECT * FROM MUSIX ",
            nativeQuery = true)
    Collection<Musix> findAllActiveUsers();

    @Query(value = "SELECT * FROM Musix  where name = ?1",
    nativeQuery = true )
    List<Musix> findTitleByName(String name);
}
