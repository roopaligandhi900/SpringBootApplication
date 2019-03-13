package com.stackroute.userservice.controller;

import com.stackroute.userservice.domain.Musix;
import com.stackroute.userservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.userservice.exceptions.TrackNotFoundException;
import com.stackroute.userservice.service.MusixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MusixController {

    private MusixService musixService;


    @Autowired
    public MusixController(MusixService musixService) {

        this.musixService = musixService;
    }

    @PostMapping("/musix")

    public ResponseEntity<?> saveMusix(@RequestBody Musix musix) throws TrackAlreadyExistsException {

        Musix savedMusix = null;

//        try {
//        savedMusix = musixService.saveMusix(musix);
//        }
//        catch (TrackAlreadyExistsException e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
//        }

        return new ResponseEntity<>(savedMusix, HttpStatus.OK);
    }

    /* This method will retrive musix by using Query parameter */

    @GetMapping("/musixs")

    public ResponseEntity<List<Musix>> getMusixs() {

        List<Musix> musixes = musixService.getMusix();
        return new ResponseEntity<List<Musix>>(musixes, HttpStatus.CREATED);


    }

    @GetMapping("/musix/{id}")

    public ResponseEntity<?> getById(@PathVariable int id) throws TrackNotFoundException {

        Musix musix = null;

//        try {
        musix = musixService.getById(id);
//        }
//        catch(TrackNotFoundException t){
//            return new ResponseEntity<>(t.getMessage(),HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<Musix>(musix, HttpStatus.OK);
    }

    /*
     This method will delete data by id
     */

    @DeleteMapping("/delete/{id}")
    public String deleteMusix(@PathVariable int id) {
        musixService.deleteById(id);
        return "Data deleted";
    }

    /*
    This method will update data by id
     */

    @PutMapping("/update/{id}")
    public ResponseEntity<Musix> updateMusix(@RequestBody Musix musix, @PathVariable int id) {

        if (musixService.updateById(musix, id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<List<Musix>> getByname(@PathVariable String name) {
        List<Musix> musix = musixService.getBYName(name);
        return new ResponseEntity<List<Musix>>(musix, HttpStatus.OK);
    }
}
