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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttractionsController {

    private final AttractionService attractionService;

    @PostMapping("/attractions/delete")
    public ResponseEntity<Object> deleteAttraction(
            @RequestParam(required = false) String attractionId) {
        try {
            attractionService.deleteAttractionById(Long.parseLong(attractionId));
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/attractions/add")
    public ResponseEntity<Object> addAttraction(@RequestBody AttractionDto attractionDto) {
        try {
            AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
            return new ResponseEntity<>(savedAttraction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/attractions/update")
    public ResponseEntity<Object> updateAttraction(
            @RequestParam(required = true) String attractionId,
            @RequestParam(required = true) String description) {
        try {
            AttractionDto attractionDto = new AttractionDto();
            attractionDto.setAttractionId(Long.parseLong(attractionId));
            attractionDto.setDescription(description);

            AttractionDto updatedAttraction = attractionService.updateAttraction(attractionDto);
            return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/attractions/filter")
    public ResponseEntity<Object> findAllAttractionsFiltered(
            @RequestParam(required = false) String orderByName,
            @RequestParam(required = false) String attractionType) {
        try {
            List<AttractionDto> attractions = this.attractionService.findAllAttractionsFiltered(orderByName, attractionType);
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/attractions/city")
    public ResponseEntity<Object> findAttractionsByCity(
            @RequestParam(required = false) String cityName) {
        try {
            List<AttractionDto> attractions = this.attractionService.findAllByCityName(cityName);
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/attractions")
    public List<AttractionDto> findAllAttractions() {
        return this.attractionService.findAllAttractions();
    }

    @GetMapping("/attractions/{attractionId}")
    public AttractionDto findAttractionById(@PathVariable Long attractionId) {
        return this.attractionService.findAttractionById(attractionId);
    }

    @GetMapping("/attractions/type/{type}")
    public List<AttractionDto> findAttractionsByType(@PathVariable AttractionType type) {
        return this.attractionService.findAttractionsByType(type);
    }
}


