package edu.kpi.parameters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterParameter {

    private String username;
    private String password;
    private String repeatedPassword;
}
