package edu.kpi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HistoryEntryDto {

    private String id;
    private Date timestamp;
    private String command;
    private String result;
}
