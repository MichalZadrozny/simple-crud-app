package pl.michalzadrozny.simplecrudapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class Car {

    private static long counter = 0;

    private final long id;
    private String mark;
    private String model;
    private String color;

    public Car(String mark, String model, String color) {
        log.info("Creating car inside the all arg constructor");
        this.id = counter;
        this.mark = mark;
        this.model = model;
        this.color = color;
        counter++;
    }

    public Car() {
        log.info("Creating car inside the no arg constructor");

        this.id = counter;
        counter++;
    }
}
