package hiber.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "cars")
public class Car {

    @Transient
    private static final Map<String, Integer> serialNumbers = new HashMap<>();

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

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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
        return serialNumbers.merge(model, Math.abs(model.hashCode() % 1000) * 1_000_000, (ov, nv) -> ov + 1);
    }
}
