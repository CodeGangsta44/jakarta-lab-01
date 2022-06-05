package edu.kpi.service.impl;

import edu.kpi.exception.PasswordsNotEqualsException;
import edu.kpi.exception.UsernameNotUniqueException;
import edu.kpi.model.User;
import edu.kpi.model.facade.UserFacadeLocal;
import edu.kpi.parameters.RegisterParameter;
import edu.kpi.service.UserServiceLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UserService implements UserServiceLocal {

    @EJB
    private UserFacadeLocal userFacadeLocal;

    @Override
    public void registerUser(final RegisterParameter parameter) {

        userFacadeLocal.findByUsername(parameter.getUsername())
                .ifPresent((user) -> {
                    throw new UsernameNotUniqueException();
                });

        if (!parameter.getPassword().equals(parameter.getRepeatedPassword())) {

            throw new PasswordsNotEqualsException();
        }

        final User user = new User();
        user.setPassword(parameter.getUsername());
        user.setPassword(parameter.getPassword());

        userFacadeLocal.create(user);

    }
}
