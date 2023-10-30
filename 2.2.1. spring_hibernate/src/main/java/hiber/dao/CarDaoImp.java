package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public int getNextCarModelSeries(String model) {
        String HQL = "from Car where model = :model";
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(HQL);
        query.setParameter("model", model);
        List<Car> cars = query.getResultList();
        return cars.isEmpty()
                ? Math.abs(model.hashCode() % 1000) * 1_000_000
                : cars.get(cars.size() - 1).getSeries() + 1;
    }
}
