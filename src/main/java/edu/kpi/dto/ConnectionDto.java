package edu.kpi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConnectionDto {

    private String id;
    private String connectionName;
    private String username;
    private String host;
    private int port;
    private String password;
    private int usages;
    private Date lastUsage;
}
