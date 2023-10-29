package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByModelAndSeries(String model, int series) {
        User user = null;
        try {
            String HQL = "from Car where model = :model and series = :series";
            TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(HQL);
            query.setParameter("model", model);
            query.setParameter("series", series);
            // В таблице могут оказаться автомобили, эквивалентные по model и series.
            // Был случай, когда сетевые карты из одной партии из Китая имели одинаковый MAC-адрес.
            user = query.getResultList().get(0).getUser();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return user;
    }
}
