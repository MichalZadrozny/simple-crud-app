package pl.michalzadrozny.simplecrudapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalzadrozny.simplecrudapp.model.Car;
import pl.michalzadrozny.simplecrudapp.service.CarServiceImpl;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/cars")
public class CarController {

    CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getCars(Model model) {
        List<Car> carList = carService.getListOfCars();
        model.addAttribute(carList);
        return "cars";
    }

    @GetMapping("/{id}")
    public String getCarByID(@PathVariable long id, Model model) {
        Optional<Car> foundCar = carService.findCarById(id);

        if (foundCar.isPresent()) {

            model.addAttribute("car", foundCar.get());

            return "car-form";
        } else {
            return "not-found";
        }

    }

    @PostMapping("/{id}/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        log.info("Car to save: "+car.toString());

        Optional<Car> oldCar = carService.findCarById(car.getId());

        if (oldCar.isPresent()) {
            log.info(car.toString() + " is present on the list");
            carService.findCarById(car.getId()).get().setMark(car.getMark());
            carService.findCarById(car.getId()).get().setModel(car.getModel());
            carService.findCarById(car.getId()).get().setColor(car.getColor());
        }else{
            log.info(car.toString() + " is not present on the list");
            carService.add(car);
        }

        return "redirect:/cars";
    }

    @GetMapping("/addCar")
    public String addCar(Model model) {
        Car car = new Car();
        model.addAttribute("car",car);
        return "car-form";
    }

    @GetMapping("/{id}/delete")
    public String deleteCar(@PathVariable long id){
        Optional<Car> foundCar = carService.findCarById(id);

        if (foundCar.isPresent()) {
            log.info("Car to delete: "+foundCar.get().toString());
            carService.delete(foundCar.get());
            return "redirect:/cars";
        }
        return "not-found";
    }
}
