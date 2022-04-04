package edu.kpi.lab01.parameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SshCommandParameter {

    private String username;
    private String host;
    private int port;
    private String password;
    private String command;
}
