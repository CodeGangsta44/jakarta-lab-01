package edu.kpi.service.impl;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import edu.kpi.model.facade.HistoryEntryFacadeLocal;
import edu.kpi.service.ConnectionServiceLocal;
import edu.kpi.service.HistoryEntryServiceLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Stateless
public class HistoryEntryService implements HistoryEntryServiceLocal {

    @EJB
    private HistoryEntryFacadeLocal historyEntryFacade;
    @EJB
    private ConnectionServiceLocal connectionService;

    @Override
    public List<HistoryEntry> findByConnection(final Connection connection) {

        return historyEntryFacade.findByConnection(connection);
    }

    @Override
    public HistoryEntry createHistoryEntry(final Connection connection, final String command) {

        final HistoryEntry entry = buildHistoryEntry(connection, command);
        historyEntryFacade.create(entry);
        return entry;
    }

    @Override
    public void updateHistoryEntryWithResult(final HistoryEntry entry, final String result) {

        entry.setResult(result);
        entry.setFinishTimestamp(LocalDateTime.now());
        historyEntryFacade.update(entry);
    }

    @Override
    public void deleteById(final long id) {

        historyEntryFacade.delete(historyEntryFacade.findById(id));
    }

    private HistoryEntry buildHistoryEntry(final Connection connection, final String command) {

        final HistoryEntry entry = new HistoryEntry();

        entry.setConnection(connection);
        entry.setCommand(command);
        entry.setStartTimestamp(LocalDateTime.now());

        return entry;
    }
}
