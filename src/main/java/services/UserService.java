package services;

import dao.UserDao;
import dao.UserDaoImpl;
import data.User;

import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDaoImpl();

    public UserService() {
    }

    public User findUser(Long id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }


}