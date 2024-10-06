package ru.aston.attractionapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.service.AttractionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttractionsController {

    @Autowired
    private final AttractionService attractionService;

    @PostMapping("/attractions")
    public ResponseEntity<Object> addAttraction(@RequestBody AttractionDto attractionDto) {
        try {
            AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
            return new ResponseEntity<>(savedAttraction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/attractions")
    public List<AttractionDto> findAllAttractions() {
        return this.attractionService.findAllAttractions();
    }

    @GetMapping("/attractions/type/{type}")
    public List<AttractionDto> findAttractionsByType(@PathVariable AttractionType type) {
        return  this.attractionService.findAttractionsByType(type);
    }

    @GetMapping("/attractions/city/id/{cityId}")
    public List<AttractionDto> findAttractionsByCityId(@PathVariable Long cityId) {
        return  this.attractionService.findAllByCityId(cityId);
    }

    @GetMapping("/attractions/city/name/{cityName}")
    public List<AttractionDto> findAttractionsByCityName(@PathVariable String cityName) {
        return  this.attractionService.findAllByCityName(cityName);
    }

    @GetMapping("/attractions/{attractionId}")
        public AttractionDto findAttractionById(@PathVariable Long attractionId) {
            return this.attractionService.findAttractionById(attractionId);
        }
}


