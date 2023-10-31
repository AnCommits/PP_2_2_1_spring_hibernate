package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    public static AnnotationConfigApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Илон", "Маск", "musk@gmail.com");
        Car carX = new Car("Model X");
        user1.setCar(carX);
        userService.add(user1);

        userService.add(new User("Леонардо", "да Винчи", "da_vinci@gmail.com"));

        userService.add(new User("Рене", "Панар", "panhard@gmail.com", Car.getRandomCar()));
        userService.add(new User("Эмиль", "Левассор", "levassor@gmail.com", Car.getRandomCar()));
        userService.add(new User("Мария", "Кюри", "curie@gmail.com", Car.getRandomCar()));

//  Получение всех пользователей из БД
        List<User> users = userService.listUsers();
        System.out.println("Все пользователи в БД:");
        for (User user : users) {
            System.out.println(user);
        }

//  Получение пользователя из БД по модели и серии машины
        User user = userService.getUserByModelSeries(carX.getModel(), carX.getSeries());
        System.out.println("Получение пользователя по модели и серии машины:");
        System.out.println(user);

//  Получение пользователя из БД по id
        long lastId = users.get(users.size() - 1).getId();
        User lastUser = userService.getUserById(lastId);
        System.out.println("Получение пользователя по id:");
        System.out.println(lastUser);

//  update
        lastUser.setEmail(lastUser.getEmail().replaceFirst("gmail", "yahoo"));
        userService.update(lastUser);

//  Получение из БД пользователя после апдейта
        User user9 = userService.getUserById(lastId);
        System.out.println("Получение пользователя после апдейта:");
        System.out.println(user9);

//  Удаление пользователя из БД
        User userBeforeLast = users.get(users.size() - 2);
        long beforeLastId = userBeforeLast.getId();
        System.out.println("Удаление из БД: " + userService.remove(beforeLastId));
//  remove again
//        System.out.println("Удаление из БД: " + userService.remove(beforeLastId));
//  remove one more time
//        System.out.println("Удаление из БД");
//        userService.remove(userBeforeLast);

//  Получение пользователя по несуществующему id
//        User user10 = userService.getUserById(987654321);
//        System.out.println(user10);

//  Получение из БД пользователя по несуществующим model и series
//        User user6 = userService.getUserByModelSeries(":-)", 987654321);
//        System.out.println();
//        System.out.println(user6);

        context.close();
    }
}
