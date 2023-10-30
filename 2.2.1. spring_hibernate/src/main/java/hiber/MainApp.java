package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {

        UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

//  Пользователь с машиной
        User userX = context.getBean("userX", User.class);
        Car carX = new Car("Model X");
        userX.setCar(carX);
        userService.add(userX);

//  Пользователь без машины
        User user5 = new User("User5", "Lastname5", "user5@mail.ru", null);
        userService.add(user5);

//  Еще пользователи
        List<User> someUsers = new ArrayList<>();
        someUsers.add(context.getBean("user1", User.class));
        someUsers.add(context.getBean("user2", User.class));
        someUsers.add(context.getBean("user3", User.class));
        someUsers.add(context.getBean("user4", User.class));

//  Розыгрыш автомобилей среди новых пользователей и сохранение пользователей в БД
        for (User user : someUsers) {
            user.setCar(context.getBean("randomCar", Car.class));
            userService.add(user);
        }

//  Получение всех пользователей из БД
        List<User> users = userService.listUsers();
        System.out.println("Все пользователи в БД:");
        for (User user : users) {
            System.out.println(user);
        }

//  Получение юзера из БД по модели и серии машины
        User user = userService.getUserByModelAndSeries(carX.getModel(), carX.getSeries());
        System.out.println("Получение юзера по модели и серии машины:");
        System.out.println(user);

//  Получение из БД владельца несуществующей машины
//        User user6 = userService.getUserByModelAndSeries(":-)", 987654321);
//        System.out.println();
//        System.out.println(user6);

//  Получение из БД юзера по id
        long lastId = users.get(users.size() - 1).getId();
        User user8 = userService.getUser(lastId);
        System.out.println("Получение юзера по id:");
        System.out.println(user8);

//  update
        user8.setEmail(user8.getEmail().replaceFirst("gmail", "yahoo"));
        userService.update(user8);

//  Получение из БД модифицированного юзера
        User user9 = userService.getUser(lastId);
        System.out.println("Получение модифицированного юзера из БД:");
        System.out.println(user9);

//  Удаление предпоследнего
        User userBeforeLast = users.get(users.size() - 2);
        long beforeLastId = userBeforeLast.getId();
        System.out.println("Удаление из БД: " + userService.remove(beforeLastId));
//  remove again
//        System.out.println("Удаление из БД: " + userService.remove(beforeLastId));
//  remove one more time
//        System.out.println("Удаление из БД");
//        userService.remove(userBeforeLast);

//  Получение юзера по несуществующему id
//        User user10 = userService.getUser(987654321);
//        System.out.println(user10);

        context.close();
    }
}
