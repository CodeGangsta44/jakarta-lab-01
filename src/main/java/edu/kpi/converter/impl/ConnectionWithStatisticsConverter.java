package edu.kpi.converter.impl;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import edu.kpi.service.HistoryEntryServiceLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Stateless(name = "connectionWithStatisticsConverter")
public class ConnectionWithStatisticsConverter extends ConnectionConverter {

    @EJB
    private HistoryEntryServiceLocal historyEntryService;

    @Override
    public ConnectionDto convert(final Connection source) {

        final List<HistoryEntry> historyEntries = historyEntryService.findByConnection(source);

        final ConnectionDto target = super.convert(source);
        target.setUsages(historyEntries.size());
        target.setLastUsage(getLastUsage(historyEntries));

        return target;
    }

    private Date getLastUsage(final List<HistoryEntry> historyEntries) {

        return historyEntries.stream()
                .map(HistoryEntry::getStartTimestamp)
                .max(LocalDateTime::compareTo)
                .map(Timestamp::valueOf)
                .orElse(null);
    }
}
