package edu.kpi.service.impl;

import edu.kpi.exception.PasswordsNotEqualsException;
import edu.kpi.exception.UsernameNotUniqueException;
import edu.kpi.model.User;
import edu.kpi.model.facade.UserFacadeLocal;
import edu.kpi.parameters.RegisterParameter;
import edu.kpi.service.UserServiceLocal;
import edu.kpi.utils.BCryptUtils;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;

import java.util.Optional;

@Stateless
public class UserService implements UserServiceLocal {

    @EJB
    private UserFacadeLocal userFacade;

    @Inject
    private SecurityContext securityContext;

    @Override
    public void registerUser(final RegisterParameter parameter) {

        userFacade.findByUsername(parameter.getUsername())
                .ifPresent((user) -> {
                    throw new UsernameNotUniqueException();
                });

        if (!parameter.getPassword().equals(parameter.getRepeatedPassword())) {

            throw new PasswordsNotEqualsException();
        }

        final User user = new User();
        user.setUsername(parameter.getUsername());
        user.setPassword(BCryptUtils.hashPassword(parameter.getPassword()));

        userFacade.create(user);
    }

    @Override
    public Optional<User> findUserByUsername(final String username) {

        return userFacade.findByUsername(username);
    }

    @Override
    public Optional<User> getCurrentUser() {

        return userFacade.findByUsername(securityContext.getCallerPrincipal().getName());
    }
}
