package hiber.service;

import hiber.dao.CarDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImp implements CarService {

    private CarDao carDao;

    @Transactional(readOnly = true)
    @Override
    public int getNextSeriesByModel(String model) {
        return carDao.getNextSeriesByModel(model);
    }

    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }
}
