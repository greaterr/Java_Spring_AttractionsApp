package ru.aston.attractionapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.attractionapp.dto.AttractionDto;
import ru.aston.attractionapp.service.AttractionService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "REST-контроллер для работы с достопримечательностями.", description = "Реализует операции создания, обновления, удаления и поиска достопримечательностей.")
@RequestMapping("/attractions")
public class AttractionsController {

    private final AttractionService attractionService;

    @Operation(summary = "Удаление достопримечательности", description = "Удаляет достопримечательность по её ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Достопримечательность успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Ресурс для удаления не найден")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAttraction(
            @RequestParam String attractionId) {
        attractionService.deleteAttractionById(Long.parseLong(attractionId));
        return ResponseEntity.noContent().build();
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
    public ResponseEntity<AttractionDto> updateAttraction(
            @RequestParam String attractionId,
            @RequestParam String description) {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setAttractionId(Long.parseLong(attractionId));
        attractionDto.setDescription(description);

        AttractionDto updatedAttraction = attractionService.updateAttraction(attractionDto);
        return ResponseEntity.ok(updatedAttraction);
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
    public ResponseEntity<AttractionDto> addAttraction(@RequestBody AttractionDto attractionDto) {
        AttractionDto savedAttraction = attractionService.addAttraction(attractionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttraction);
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
    public ResponseEntity<List<AttractionDto>> findAllAttractionsFiltered(
            @RequestParam(required = false) String orderByName,
            @RequestParam(required = false) String attractionType) {
        List<AttractionDto> attractions = this.attractionService.findAllAttractionsFiltered(orderByName, attractionType);
        return ResponseEntity.ok(attractions);
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
    public ResponseEntity<List<AttractionDto>> findAttractionsByCity(
            @RequestParam String cityName) {
        List<AttractionDto> attractions = this.attractionService.findAllByCityName(cityName);
        return ResponseEntity.ok(attractions);
    }

    @Operation(
            summary = "Поиск всех достопримечательностей",
            description = "Возвращает все достопримечательности отсортированных по ID"
    )
    @GetMapping("/")
    public ResponseEntity<List<AttractionDto>> findAllAttractions() {
        List<AttractionDto> attractions = this.attractionService.findAllAttractions();
        return ResponseEntity.ok(attractions);
    }
}
