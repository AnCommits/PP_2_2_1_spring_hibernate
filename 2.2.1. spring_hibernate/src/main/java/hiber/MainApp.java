package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.CarModel;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> originalUsers = new ArrayList<>();
        originalUsers.add(context.getBean("user1", User.class));
        originalUsers.add(context.getBean("user2", User.class));
        originalUsers.add(context.getBean("user3", User.class));
        originalUsers.add(context.getBean("user4", User.class));

//  Розыгрыш автомобилей среди первых пользователей
        for (User user : originalUsers) {
            user.setCar(context.getBean("randomCar", Car.class));
            userService.add(user);
        }

//  Пользователь без авто
        User user5 = new User("User5", "Lastname5", "user5@mail.ru", null);
        originalUsers.add(user5);
        userService.add(user5);

//  Еще пользователь с авто
        User userX = context.getBean("userX", User.class);
        Car carX = new Car("Model X", 1);
        userX.setCar(carX);
        userService.add(userX);

//  Получение всех пользователей из БД
        List<User> users = userService.listUsers();
        System.out.println();
        for (User user : users) {
            System.out.println(user);
        }

// Получение юзера, по модели и серии машины
        User user = userService.getUserByModelAndSeries(carX.getModel(), carX.getSeries());
        System.out.println();
        System.out.println(user);

// Получение владельца несуществующей машины
        User user6 = userService.getUserByModelAndSeries(":-)", 987654321);
        System.out.println();
        System.out.println(user6);

        context.close();
    }
}
