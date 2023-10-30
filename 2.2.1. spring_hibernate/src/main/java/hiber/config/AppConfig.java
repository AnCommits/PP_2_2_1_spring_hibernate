package hiber.config;

import hiber.model.Car;
import hiber.model.CarModel;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "hiber")
public class AppConfig {

    private static final Random random = new Random();

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props = new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class, Car.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

    @Bean("user1")
    public User getUser1() {
        return new User("Рене", "Панар", "panhard@gmail.com");
    }

    @Bean("user2")
    public User getUser2() {
        return new User("Эмиль", "Левассор", "levassor@gmail.com");
    }

    @Bean("user3")
    public User getUser3() {
        return new User("Альберт", "Эйнштейн", "einstein@gmail.com");
    }

    @Bean("user4")
    public User getUser4() {
        return new User("Мария", "Кюри", "curie@gmail.com");
    }

    @Bean("userX")
    public User getUserX() {
        return new User("Илон", "Маск", "musk@gmail.com");
    }

    @Bean("randomCar")
    @Scope("prototype")
    public Car getRandomCar() {
        CarModel[] carModels = CarModel.values();
        return new Car(carModels[random.nextInt(carModels.length)].getModel());
    }
}
