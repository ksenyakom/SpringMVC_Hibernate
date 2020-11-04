package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;
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
 //    @Transactional
    public void saveUser(User user) {
    //    em.getTransaction().begin();
        em.persist(user);
     //   em.getTransaction().commit();
    }

    @Override
    public void update(long id, User userUpdated) {
      //  em.getTransaction().begin();
        User userToUpdate = em.find(User.class, id);
        userToUpdate.setName(userUpdated.getName());
        userToUpdate.setLastName(userUpdated.getLastName());
        userToUpdate.setAge(userUpdated.getAge());
     //   em.flush();
    //    em.merge(userUpdated);
    //    em.getTransaction().commit();
    }

    @Override
    public User getById(long id) {
        User user = em.find(User.class, id);
        em.detach(user);
        return user;
    }

    @Override
    public void removeUserById(long id) {
    //    em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
    //    em.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = em.createQuery("SELECT u from User u")
                .getResultList();
        return users;
    }

}
