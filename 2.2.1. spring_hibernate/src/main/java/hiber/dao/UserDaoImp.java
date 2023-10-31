package hiber.dao;

import hiber.model.User;
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
    public User getUserByModelSeries(String model, int series) {
        String hql = "from User where car = (from Car where model = :model and series = :series)";
        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery(hql);
        typedQuery.setParameter("model", model);
        typedQuery.setParameter("series", series);
        User user = null;
        try {
            user = typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(long id) {
        User user = null;
        try {
            String HQL = "from User where id = :id";
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(HQL);
            query.setParameter("id", id);
            user = query.getSingleResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void remove(User user) {
        try {
            // никакие исключения почему-то не перехватываются при отсутствии юзера в БД,
            // в getUser исключение перехватывается при отсутствии id в БД
            User user2 = getUserById(user.getId());
            sessionFactory.getCurrentSession().remove(user2);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public boolean remove(long id) {
        try {
            sessionFactory.getCurrentSession().remove(getUserById(id));
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return false;
    }
}
