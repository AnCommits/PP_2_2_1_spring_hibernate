package hiber.model;

import hiber.MainApp;
import hiber.service.CarService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String model;

    private int series;

    @OneToOne(mappedBy = "car")
    private User owner;

    public Car() {
    }

    public Car(@NonNull String model) {
        this.model = model;
        series = getNextSeries(model);
    }

    public Car(@NonNull String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(@NonNull String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public User getUser() {
        return owner;
    }

    public void setUser(User user) {
        owner = user;
    }

    public static int getNextSeries(String model) {
        AnnotationConfigApplicationContext context = MainApp.getContext();
        CarService carService = context.getBean(CarService.class);
        return carService.getNextCarModelSeries(model);
    }
}
