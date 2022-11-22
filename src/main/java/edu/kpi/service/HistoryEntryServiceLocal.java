package edu.kpi.service;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface HistoryEntryServiceLocal {

    List<HistoryEntry> findByConnection(final Connection connection);

    HistoryEntry createHistoryEntry(final Connection connection, final String command);

    void updateHistoryEntryWithResult(final HistoryEntry entry, final String result);

    void deleteById(final long id);
}
