package ru.aston.attractionapp.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
public class AttractionsController {

    @Autowired
    private final AttractionService attractionService;

    @PostMapping
    public ResponseEntity<AttractionDto> addAttraction(@RequestBody AttractionDto attractionDto) {
        AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
        return new ResponseEntity<>(savedAttraction, HttpStatus.CREATED);
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


