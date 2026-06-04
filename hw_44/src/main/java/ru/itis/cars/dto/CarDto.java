package ru.itis.cars.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.itis.cars.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarDto {

    @Schema(description = "Идентикатор машины", example = "1")
    private Long id;
    @Schema(description = "Модель машины", example = "Toyota")
    private String model;
    @Schema(description = "Цвет машины", example = "Чёрный")
    private String color;
    @Schema(description = "Цена машины", example = "15000")
    private Double price;

    private CarDto() {
    }
    public CarDto(Long id, String model, String color, Double price) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public static CarDto from(Car car) {
        return new CarDto(car.getId(), car.getModel(), car.getColor(), car.getPrice());
    }

    public static List<CarDto> from(List<Car> cars) {
        return cars
                .stream()
                .map(CarDto::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Double getPrice() {
        return price;
    }
}
