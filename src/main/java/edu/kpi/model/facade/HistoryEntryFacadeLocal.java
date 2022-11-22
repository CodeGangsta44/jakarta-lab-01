package edu.kpi.model.facade;

import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface HistoryEntryFacadeLocal {

    void create(final HistoryEntry historyEntry);

    void update(final HistoryEntry historyEntry);

    void delete(final HistoryEntry historyEntry);

    HistoryEntry findById(final Object id);

    List<HistoryEntry> findByConnection(final Connection connection);
}
