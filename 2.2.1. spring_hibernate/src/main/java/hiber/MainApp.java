package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
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

//  Автомобильная лотерея
      for (User user : originalUsers) {
         user.setCar(context.getBean("randomCar", Car.class));
         userService.add(user);
      }

      List<User> users = userService.listUsers();
      System.out.println();
      for (User user : users) {
         System.out.println(user);
      }

      context.close();
   }
}
