package ru.itis.cars.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.cars.controller.api.CarApi;
import ru.itis.cars.dto.CarDto;
import ru.itis.cars.dto.NewCarDto;
import ru.itis.cars.dto.PageDto;
import ru.itis.cars.entity.Car;
import ru.itis.cars.service.CarService;

import java.util.List;

@RestController
public class CarController implements CarApi {
    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    public PageDto<CarDto> getAll(int page, int size, String sort) {return carService.getAllCars(page, size, sort);}

    public CarDto create(@RequestBody NewCarDto newCarDto) {return carService.createCar(newCarDto);}

    @Override
    public CarDto getById(Long carId) {
        return carService.getCar(carId);
    }
}
