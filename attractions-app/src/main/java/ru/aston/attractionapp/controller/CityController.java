package ru.aston.attractionapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.service.CityService;

@Controller
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private final CityService cityService;

    @PostMapping("/cities")
    public ResponseEntity<Object> addCity(@RequestBody CityDto cityDto) {
        try {
            CityDto savedCity = cityService.addCity(cityDto);
            return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
