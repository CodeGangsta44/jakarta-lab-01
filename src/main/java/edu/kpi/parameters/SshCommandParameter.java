package edu.kpi.parameters;

import edu.kpi.dto.ConnectionDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SshCommandParameter {

    private ConnectionDto connection;
    private String command;
}
