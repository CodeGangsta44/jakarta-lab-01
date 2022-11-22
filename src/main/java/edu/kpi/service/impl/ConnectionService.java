package edu.kpi.service.impl;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.model.User;
import edu.kpi.model.facade.ConnectionFacadeLocal;
import edu.kpi.service.ConnectionServiceLocal;
import edu.kpi.service.UserServiceLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Stateless
public class ConnectionService implements ConnectionServiceLocal {

    @EJB
    private ConnectionFacadeLocal connectionFacade;
    @EJB
    private UserServiceLocal userService;

    @Override
    public void addConnection(final ConnectionDto parameter) {

        final User user = userService.getCurrentUser().orElseThrow();

        final Connection connection = new Connection();
        connection.setConnectionName(parameter.getConnectionName());
        connection.setUsername(parameter.getUsername());
        connection.setHost(parameter.getHost());
        connection.setPort(parameter.getPort());
        connection.setPassword(parameter.getPassword());
        connection.setOwner(user);

        connectionFacade.create(connection);
    }

    @Override
    public List<Connection> findByUser(final User user) {

        return connectionFacade.findByUser(user);
    }

    @Override
    public Connection findById(final long id) {

        return connectionFacade.findById(id);
    }

    @Override
    public void deleteById(final long id) {

        connectionFacade.delete(connectionFacade.findById(id));
    }

    @Override
    public void updateConnection(final ConnectionDto parameter) {

        final Connection connection = connectionFacade.findById(getId(parameter));

        connection.setConnectionName(parameter.getConnectionName());
        connection.setUsername(parameter.getUsername());
        connection.setHost(parameter.getHost());
        connection.setPort(parameter.getPort());
        connection.setPassword(parameter.getPassword());

        connectionFacade.update(connection);
    }

    public static Long getId(final ConnectionDto parameter) {

        return Optional.ofNullable(parameter.getId())
                .filter(Predicate.not(String::isEmpty))
                .map(Long::parseLong)
                .orElse(-1L);
    }
}
