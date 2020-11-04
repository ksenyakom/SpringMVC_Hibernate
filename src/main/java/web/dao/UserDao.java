package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void update(long id, User user);

    User getById(long id);

    List<User> getAllUsers();

    void removeUserById(long id);


}
