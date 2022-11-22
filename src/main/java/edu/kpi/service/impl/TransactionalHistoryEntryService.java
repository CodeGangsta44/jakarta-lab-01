package edu.kpi.service.impl;

import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import edu.kpi.service.HistoryEntryServiceLocal;
import edu.kpi.service.TransactionalHistoryEntryServiceLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.function.Supplier;

@Stateless
public class TransactionalHistoryEntryService implements TransactionalHistoryEntryServiceLocal<String> {

    @EJB
    private HistoryEntryServiceLocal historyEntryService;

    @Override
    public String executeAndSaveHistory(final Connection connection, final String command, final Supplier<String> resultSupplier) {

        final HistoryEntry entry = historyEntryService.createHistoryEntry(connection, command);

        final String result = resultSupplier.get();

        historyEntryService.updateHistoryEntryWithResult(entry, result);

        return result;
    }
}
