package ru.itis.cars.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.itis.cars.dto.CarDto;
import ru.itis.cars.dto.NewCarDto;
import ru.itis.cars.dto.PageDto;
import ru.itis.cars.entity.Car;

import java.util.List;

@RequestMapping("/api/v1/cars")
public interface CarApi {

    @Operation(summary = "Список машин", description = "Получение списка машин")
    @ApiResponse(description = "Страница с машинами", responseCode = "200")
    @GetMapping
    PageDto<CarDto> getAll(
            @Parameter(description = "Номер страницы") @RequestParam("page") int page,
            @Parameter(description = "Размер страницы") @RequestParam("size") int size,
            @Parameter(description = "Сортировка по...") @RequestParam("sort") String sort
    );

    @Operation(summary = "Добавление машины", description = "Добавление машины в базу данных")
    @ApiResponse(description = "Информация о добавленной машине", responseCode = "201")
    @PostMapping
    CarDto create(@RequestBody NewCarDto newCarDto);

    @Operation(summary = "Информации о машине", description = "Получение информации о машине")
    @ApiResponses(value = {
            @ApiResponse(description = "Информация о машине по её id", responseCode = "200"),
            @ApiResponse(description = "Машина не найдена", responseCode = "404")

    })
    @GetMapping("/{car-id}")
    CarDto getById(@Parameter(description = "Идентификатор машины") @PathVariable("car-id") Long carId);
}
