package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void update(long id, User userUpdated) {
        em.find(User.class, id);
        em.merge(userUpdated);
    }

    @Override
    public User getById(long id) {
        User user = em.find(User.class, id);
        em.detach(user);

        return user;
    }

    @Override
    public void removeUserById(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = em.createQuery("SELECT u from User u")
                .getResultList();

        return users;
    }

}
