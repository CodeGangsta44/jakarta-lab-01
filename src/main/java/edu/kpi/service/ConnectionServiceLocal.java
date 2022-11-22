package edu.kpi.service;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.model.User;
import edu.kpi.parameters.AddConnectionParameter;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ConnectionServiceLocal {

    void addConnection(final ConnectionDto parameter);

    List<Connection> findByUser(final User user);

    Connection findById(final long id);

    void deleteById(final long id);

    void updateConnection(final ConnectionDto parameter);
}
