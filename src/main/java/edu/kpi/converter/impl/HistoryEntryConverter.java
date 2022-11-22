package edu.kpi.converter.impl;

import edu.kpi.converter.HistoryEntryConverterLocal;
import edu.kpi.dto.HistoryEntryDto;
import edu.kpi.model.HistoryEntry;
import jakarta.ejb.Stateless;

import java.sql.Timestamp;

import static edu.kpi.constants.Constants.Format.BREAK;
import static edu.kpi.constants.Constants.Format.LINE_ENDING;

@Stateless
public class HistoryEntryConverter implements HistoryEntryConverterLocal {

    @Override
    public HistoryEntryDto convert(final HistoryEntry source) {

        return HistoryEntryDto.builder()
                .id(String.valueOf(source.getId()))
                .timestamp(Timestamp.valueOf(source.getStartTimestamp()))
                .command(source.getCommand())
                .result(getResult(source))
                .build();
    }

    private String getResult(final HistoryEntry source) {

        return String.join(BREAK, source.getResult().split(LINE_ENDING));
    }
}
