package ru.itis.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.cars.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
