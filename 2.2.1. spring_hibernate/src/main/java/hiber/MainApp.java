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

      User user1 = context.getBean("user1", User.class);
      User user2 = context.getBean("user2", User.class);
      User user3 = context.getBean("user3", User.class);
      User user4 = context.getBean("user4", User.class);

      Car car1 = context.getBean("carP4_0001", Car.class);
      Car car2 = context.getBean("carP4_0002", Car.class);
      Car car3 = context.getBean("carP7_0001", Car.class);
      Car car4 = context.getBean("carP7_0002", Car.class);

      user1.setCar(car1);
      userService.add(user1);

      user2.setCar(car2);
      userService.add(user2);

      user3.setCar(car4);
      userService.add(user3);

      user4.setCar(car3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      System.out.println();
      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
         System.out.println(user);
      }

      context.close();
   }
}
