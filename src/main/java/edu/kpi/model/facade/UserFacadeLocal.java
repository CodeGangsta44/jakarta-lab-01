package edu.kpi.model.facade;

import edu.kpi.model.User;
import jakarta.ejb.Local;

import java.util.Optional;

@Local
public interface UserFacadeLocal {

    void create(final User user);

    void update(final User user);

    void delete(final User user);

    User findById(final Object id);

    Optional<User> findByUsername(final String username);
}
