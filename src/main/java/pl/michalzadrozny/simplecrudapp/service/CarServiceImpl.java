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

        int max = 50;
        Car[] arr = new Car[max];
        for(int i = 0; i <max;i++){
            arr[i] = new Car("Car "+i,"Car "+i,"Car "+i);
            listOfCars.add(arr[i]);
        }
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
