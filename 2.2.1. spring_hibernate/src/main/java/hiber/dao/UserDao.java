package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserByModelSeries(String model, int series);

    User getUserById(long id);

    void remove(User user);

    void update(User user);

    boolean remove(long id);
}
