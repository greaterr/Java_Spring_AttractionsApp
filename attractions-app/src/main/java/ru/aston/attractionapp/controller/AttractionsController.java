package ru.aston.attractionapp.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.Attraction;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.service.AttractionService;

import java.util.List;

@RestController
@AllArgsConstructor
public class AttractionsController {

    @Autowired
    private final AttractionService attractionService;

    @GetMapping("/attractions")
    public List<AttractionDto> findAllAttractions() {
        return this.attractionService.findAllAttractions();
    }

    @GetMapping("/attractions/type/{type}")
    public List<AttractionDto> findAttractionsByType(@PathVariable AttractionType type) {
        return  this.attractionService.findAttractionsByType(type);
    }

    @GetMapping("/attractions/city/{cityId}")
    public List<AttractionDto> findAttractionsByCityId(@PathVariable Long cityId) {
        return  this.attractionService.findAllByCityId(cityId);
    }

    @GetMapping("/attractions/{attractionId}")
        public AttractionDto findAttractionById(@PathVariable Long attractionId) {
            return this.attractionService.findAttractinById(attractionId);
        }
}


