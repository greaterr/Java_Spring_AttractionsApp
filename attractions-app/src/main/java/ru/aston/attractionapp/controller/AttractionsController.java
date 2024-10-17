package ru.aston.attractionapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.entity.AttractionType;
import ru.aston.attractionapp.service.AttractionService;

import java.util.List;

/**
 * REST-контроллер для работы с достопримечательностями.
 * Реализует операции создания, обновления, удаления и поиска достопримечательностей.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AttractionsController {

    private final AttractionService attractionService;

    /**
     * Удаляет достопримечательность по её ID.
     *
     * @param attractionId Идентификатор достопримечательности
     * @return Статус принятия операции, либо статус ошибки, если не удалось удалить
     */
    @DeleteMapping("/attractions/delete")
    public ResponseEntity<Object> deleteAttraction(
            @RequestParam(required = false) String attractionId) {
        try {
            attractionService.deleteAttractionById(Long.parseLong(attractionId));
            log.info("Successfully deleted attraction with ID: {}", attractionId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            log.error("Error deleting attraction with ID: {}", attractionId, e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Добавляет новую достопримечательность.
     *
     * @param attractionDto DTO объекта достопримечательности
     * @return Добавленный объект или ошибка, если он уже существует
     */
    @PostMapping("/attractions/add")
    public ResponseEntity<Object> addAttraction(@RequestBody AttractionDto attractionDto) {
        try {
            AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
            log.info("Successfully added new attraction: {}", savedAttraction.getName());
            return new ResponseEntity<>(savedAttraction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("Failed to add attraction: {}", attractionDto.getName(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Обновляет описание достопримечательности.
     *
     * @param attractionId Идентификатор достопримечательности
     * @param description  Новое описание
     * @return Обновленный объект достопримечательности или ошибка
     */
    @PutMapping("/attractions/update")
    public ResponseEntity<Object> updateAttraction(
            @RequestParam String attractionId,
            @RequestParam String description) {
        try {
            AttractionDto attractionDto = new AttractionDto();
            attractionDto.setAttractionId(Long.parseLong(attractionId));
            attractionDto.setDescription(description);

            AttractionDto updatedAttraction = attractionService.updateAttraction(attractionDto);
            log.info("Successfully updated attraction with ID: {}", attractionId);
            return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Failed to update attraction with ID: {}", attractionId, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Возвращает список достопримечательностей, отфильтрованных по типу и/или по имени.
     *
     * @param orderByName    Порядок сортировки по имени (asc/desc)
     * @param attractionType Тип достопримечательности
     * @return Список достопримечательностей
     */
    @GetMapping("/attractions/filter")
    public ResponseEntity<Object> findAllAttractionsFiltered(
            @RequestParam(required = false) String orderByName,
            @RequestParam(required = false) String attractionType) {
        try {
            List<AttractionDto> attractions = this.attractionService.findAllAttractionsFiltered(orderByName, attractionType);
            log.info("Successfully retrieved filtered attractions");
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error filtering attractions", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Возвращает список достопримечательностей для заданного города.
     *
     * @param cityName Название города
     * @return Список достопримечательностей
     */
    @GetMapping("/attractions/city")
    public ResponseEntity<Object> findAttractionsByCity(
            @RequestParam String cityName) {
        try {
            List<AttractionDto> attractions = this.attractionService.findAllByCityName(cityName);
            log.info("Successfully retrieved attractions for city: {}", cityName);
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error retrieving attractions for city: {}", cityName, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Возвращает все достопримечательности.
     *
     * @return Список всех достопримечательностей
     */
    @GetMapping("/attractions")
    public List<AttractionDto> findAllAttractions() {
        log.info("Retrieving all attractions");
        return this.attractionService.findAllAttractions();
    }

    /**
     * Возвращает достопримечательность по её ID.
     *
     * @param attractionId Идентификатор достопримечательности
     * @return DTO достопримечательности
     */
    @GetMapping("/attractions/{attractionId}")
    public AttractionDto findAttractionById(@PathVariable Long attractionId) {
        log.info("Retrieving attraction with ID: {}", attractionId);
        return this.attractionService.findAttractionById(attractionId);
    }

    /**
     * Возвращает список достопримечательностей по их типу.
     *
     * @param type Тип достопримечательности (например, PARK)
     * @return Список достопримечательностей данного типа
     */
    @GetMapping("/attractions/type/{type}")
    public List<AttractionDto> findAttractionsByType(@PathVariable AttractionType type) {
        log.info("Retrieving attractions of type: {}", type);
        return this.attractionService.findAttractionsByType(type);
    }
}
