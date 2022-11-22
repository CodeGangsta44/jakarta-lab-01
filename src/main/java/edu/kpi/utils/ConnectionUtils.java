package edu.kpi.utils;

import edu.kpi.dto.ConnectionDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;
import java.util.function.Predicate;

import static edu.kpi.constants.Constants.Parameters.*;

public class ConnectionUtils {

    public static ConnectionDto getConnectionParameter(final HttpServletRequest request) {

        return ConnectionDto.builder()
                .id(request.getParameter(ID))
                .connectionName(request.getParameter(CONNECTION_NAME))
                .username(request.getParameter(USERNAME))
                .host(request.getParameter(HOST))
                .port(Integer.parseInt(request.getParameter(PORT)))
                .password(request.getParameter(PASSWORD))
                .build();
    }
}
