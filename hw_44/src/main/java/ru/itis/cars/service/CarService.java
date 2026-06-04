package ru.itis.cars.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.cars.dto.CarDto;
import ru.itis.cars.dto.NewCarDto;
import ru.itis.cars.dto.PageDto;
import ru.itis.cars.entity.Car;
import ru.itis.cars.repository.CarRepository;
import ru.itis.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {this.carRepository = carRepository;}

    @Transactional
    public CarDto createCar(NewCarDto newCarDto) {
        Car car = new Car();
        car.setModel(newCarDto.getModel());
        car.setPrice(newCarDto.getPrice());
        car.setColor(newCarDto.getColor());

        carRepository.save(car);

        return CarDto.from(car);
    }

    public PageDto<CarDto> getAllCars(int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, sort));
        Page<Car> carsPage = carRepository.findAll(pageRequest);
        return PageDto.from(CarDto.from(carsPage.getContent()), carsPage.getTotalPages(), carsPage.getTotalElements());
    }

    public CarDto getCar(Long carId) {
//        Optional<Car> car = carRepository.findById(carId);
//        if (car.isPresent()) {
//            return CarDto.from(car.get());
//        }
//        throw new NotFoundException("Car " + carId.toString() + " not found");
        return CarDto.from(carRepository.findById(carId).orElseThrow(EntityNotFoundException::new));
    }
}
