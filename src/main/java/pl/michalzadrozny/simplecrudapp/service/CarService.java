package pl.michalzadrozny.simplecrudapp.service;

import pl.michalzadrozny.simplecrudapp.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> findCarsByColor(String color);
    Optional<Car> findCarById(long id);
    Optional<Car> findEqualCar(Car car);
    void add(Car car);
    void delete(Car car);
}
