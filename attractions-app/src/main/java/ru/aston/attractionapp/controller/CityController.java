package ru.aston.attractionapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.attractionapp.dto.CityDto;
import ru.aston.attractionapp.service.CityService;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @PostMapping("/add")
    public ResponseEntity<Object> addCity(@RequestBody CityDto cityDto) {
        try {
            CityDto savedCity = cityService.addCity(cityDto);
            return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateCity(@RequestBody CityDto cityDto) {
        try {
            CityDto savedCity = cityService.updateCity(cityDto);
            return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
