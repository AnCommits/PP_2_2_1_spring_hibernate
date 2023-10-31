package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByModelSeries(String model, int series) {
      return userDao.getUserByModelSeries(model, series);
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserById(long id) {
      return userDao.getUserById(id);
   }

   @Transactional
   @Override
   public void remove(User user) {
      userDao.remove(user);
   }

   @Transactional
   @Override
   public void update(User user) {
      userDao.update(user);
   }

   @Transactional
   @Override
   public boolean remove(long id) {
      return userDao.remove(id);
   }
}
