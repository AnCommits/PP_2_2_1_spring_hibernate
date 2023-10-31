package hiber.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public int getNextSeriesByModel(String model) {
        String HQL = "select max(series) from Car where model = :model";
        TypedQuery<Integer> query = sessionFactory.getCurrentSession().createQuery(HQL);
        query.setParameter("model", model);
        Integer lastSeries = query.getSingleResult();
        return lastSeries == null ? Math.abs(model.hashCode() % 1000) * 1_000_000 : lastSeries + 1;
    }
}
