package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void update(long id, User user);

    User getById(long id);

    List<User> getAll();

    void removeUserById(long id);
}
