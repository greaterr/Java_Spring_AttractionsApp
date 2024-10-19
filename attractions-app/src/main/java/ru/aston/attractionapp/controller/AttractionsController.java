package ru.aston.attractionapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.service.AttractionService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RequestMapping("/attractions")
@Tag(name = "REST-контроллер для работы с достопримечательностями.", description = "Реализует операции создания, обновления, удаления и поиска достопримечательностей.")
public class AttractionsController {

    private final AttractionService attractionService;


    @Operation(summary = "Удаление достопримечательности", description = "Удаляет достопримечательность по её ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Достопримечательность успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Ресурс для удаления не найден")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteAttraction(
            @RequestParam(required = false) String attractionId) {
        try {
            attractionService.deleteAttractionById(Long.parseLong(attractionId));
            log.info("Successfully deleted attraction with ID: {}", attractionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("Error deleting attraction with ID: {}", attractionId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Обновление достопримечательности",
            description = "Обновляет описание достопримечательности."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Достопримечательность успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Достопримечательность с указанным ID не существует")
    })
    @PutMapping("/update")
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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @Operation(
            summary = "Добавление достопримечательности",
            description = "Добавляет новую достопримечательность в систему"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Достопримечательность успешно добавлена"),
            @ApiResponse(responseCode = "409", description = "Конфликт: достопримечательность уже существует")
    })
    @PostMapping("/add")
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

    @Operation(
            summary = "Фильтр/сортировка достопримечательностей",
            description = "Возвращает список достопримечательностей, отфильтрованных по типу и/или по имени."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выведен список достопримечательностей"),
            @ApiResponse(responseCode = "400", description = "Неверные параметры запроса (например, некорректный тип фильтрации).")
    })
    @GetMapping("/filter")
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

    @Operation(
            summary = "Поиск достопримечательностей конкретного населенного пункта",
            description = "Возвращает список достопримечательностей для заданного населенного пункта."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выведен список достопримечательностей для заданного города"),
            @ApiResponse(responseCode = "404", description = "Произошла ошибка при получении данных для указанного города. Город с такми именем не найден.")
    })
    @GetMapping("/city")
    public ResponseEntity<Object> findAttractionsByCity(
            @RequestParam String cityName) {
        try {
            List<AttractionDto> attractions = this.attractionService.findAllByCityName(cityName);
            log.info("Successfully retrieved attractions for city: {}", cityName);
            return new ResponseEntity<>(attractions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error retrieving attractions for city: {}", cityName, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Поиск всех достопримечательностей",
            description = "Возвращает все достопримечательности отсортированных по ID"
    )
    @GetMapping("/")
    public List<AttractionDto> findAllAttractions() {
        log.info("Retrieving all attractions");
        return this.attractionService.findAllAttractions();
    }
}
