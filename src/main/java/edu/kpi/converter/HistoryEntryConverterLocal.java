package edu.kpi.converter;

import edu.kpi.dto.HistoryEntryDto;
import edu.kpi.model.HistoryEntry;
import jakarta.ejb.Local;

@Local
public interface HistoryEntryConverterLocal extends Converter<HistoryEntry, HistoryEntryDto> {
}
