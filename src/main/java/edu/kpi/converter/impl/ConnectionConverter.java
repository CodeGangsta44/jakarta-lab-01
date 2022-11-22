package edu.kpi.converter.impl;

import edu.kpi.converter.ConnectionConverterLocal;
import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.service.HistoryEntryServiceLocal;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless(name = "defaultConnectionConverter")
public class ConnectionConverter implements ConnectionConverterLocal {

    @EJB
    private HistoryEntryServiceLocal historyEntryService;

    @Override
    public ConnectionDto convert(final Connection source) {

        return ConnectionDto.builder()
                .id(String.valueOf(source.getId()))
                .connectionName(source.getConnectionName())
                .username(source.getUsername())
                .host(source.getHost())
                .port(source.getPort())
                .password(source.getPassword())
                .build();
    }
}
