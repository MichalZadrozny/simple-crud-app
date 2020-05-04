package pl.michalzadrozny.simplecrudapp.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.michalzadrozny.simplecrudapp.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Getter
public class CarServiceImpl implements CarService {

    List<Car> listOfCars;

    public CarServiceImpl() {
        listOfCars = new ArrayList<>();

        Car maluch = new Car("Fiat", "126p", "Silver");
        Car mustang = new Car("Ford", "Mustang", "Red");
        Car cytrynka = new Car("Citroen", "Saxo", "Silver");
        Car car4 = new Car("car4", "car4", "car4");
        Car car5 = new Car("car5", "car5", "car5");
        Car car6 = new Car("car6", "car6", "car6");

        listOfCars.add(maluch);
        listOfCars.add(mustang);
        listOfCars.add(cytrynka);
        listOfCars.add(car4);
        listOfCars.add(car5);
        listOfCars.add(car6);
    }

    @Override
    public List<Car> findCarsByColor(String color) {
        return listOfCars.stream().filter(car -> car.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
    }

    @Override
    public Optional<Car> findCarById(long id) {
        return listOfCars.stream().filter(car -> car.getId() == id).findFirst();
    }

    @Override
    public Optional<Car> findEqualCar(Car newCar) {
        return listOfCars.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
    }

    @Override
    public void add(Car car) {
        listOfCars.add(car);
    }

    @Override
    public void delete(Car car) {
        log.info("Deleting: "+car.toString());
        listOfCars.remove(car);
    }
}
