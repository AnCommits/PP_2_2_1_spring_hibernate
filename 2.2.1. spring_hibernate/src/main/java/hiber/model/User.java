package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    //    @Column(name = "email", unique = true)
    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Car car;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, Car car) {
        this(firstName, lastName, email);
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return car != null
                ? String.format("%d: %s %s, email %s, автомобиль %s, сер.номер %d",
                id, firstName, lastName, email, car.getModel(), car.getSeries())
                : String.format("%d: %s %s, email %s, данные по автомобилю отсутствуют",
                id, firstName, lastName, email);
    }
}
