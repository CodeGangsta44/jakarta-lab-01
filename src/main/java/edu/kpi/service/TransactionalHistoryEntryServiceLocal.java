package edu.kpi.service;

import edu.kpi.model.Connection;
import jakarta.ejb.Local;

import java.util.function.Supplier;

@Local
public interface TransactionalHistoryEntryServiceLocal<T> {

    T executeAndSaveHistory(final Connection connection, final String command, final Supplier<T> resultSupplier);
}
