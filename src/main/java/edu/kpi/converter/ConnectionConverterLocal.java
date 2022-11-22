package edu.kpi.converter;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import jakarta.ejb.Local;

@Local
public interface ConnectionConverterLocal extends Converter<Connection, ConnectionDto> {
}
