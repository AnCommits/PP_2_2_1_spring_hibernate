package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserByModelAndSeries(String model, int series);

    User getUser(long id);

    void remove(User user);

    void update(User user);

    boolean remove(long id);
}
