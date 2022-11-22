package edu.kpi.service;

import edu.kpi.model.User;
import edu.kpi.parameters.RegisterParameter;
import jakarta.ejb.Local;

import java.util.Optional;

@Local
public interface UserServiceLocal {

    void registerUser(final RegisterParameter parameter);

    Optional<User> findUserByUsername(final String username);

    Optional<User> getCurrentUser();
}
