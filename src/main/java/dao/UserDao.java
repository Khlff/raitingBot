package dao;

import data.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public interface UserDao {
    User findById(Long id);

    void save(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();
}
