package edu.kpi.model.facade;

import edu.kpi.model.Connection;
import edu.kpi.model.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ConnectionFacadeLocal {

    void create(final Connection connection);

    void update(final Connection connection);

    void delete(final Connection connection);

    Connection findById(final Object id);

    List<Connection> findByUser(final User user);
}
