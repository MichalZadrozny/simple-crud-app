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
            log.info("Car to edit: "+foundCar.get().toString());

            return "car-form";
        } else {
            return "not-found";
        }

    }

    @PostMapping("/{id}/save")
    public String saveCar(@PathVariable long id, String mark, String model, String color) {

        Optional<Car> oldCar = carService.findCarById(id);

        if (oldCar.isPresent()) {
            oldCar.get().setMark(mark);
            oldCar.get().setModel(model);
            oldCar.get().setColor(color);
        }else{
            carService.add(new Car(mark,model,color));
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
