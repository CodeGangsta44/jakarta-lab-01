package edu.kpi.parameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddConnectionParameter {

    private String connectionName;
    private String username;
    private String host;
    private int port;
    private String password;
}
