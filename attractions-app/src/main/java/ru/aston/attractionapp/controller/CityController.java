package ru.aston.attractionapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Добавление населенного пункта",
            description = "Добавляет новый населенный пункт в систему"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Населенный пункт успешно добавлен"),
            @ApiResponse(responseCode = "409", description = "Конфликт: населенный пункт с таким именем уже существует")
    })
    public ResponseEntity<Object> addCity(@RequestBody CityDto cityDto) {
        try {
            CityDto savedCity = cityService.addCity(cityDto);
            return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/update")
    @Operation(
            summary = "Обновление анных города",
            description = "Обновляет данные города - население и/или наличие метро."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Город успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Город с указанным ID не найден")
    })
    public ResponseEntity<Object> updateCity(@RequestBody CityDto cityDto) {
        try {
            CityDto savedCity = cityService.updateCity(cityDto);
            return new ResponseEntity<>(savedCity, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
