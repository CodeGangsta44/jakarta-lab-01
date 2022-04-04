package edu.kpi.lab01.utils;

import edu.kpi.lab01.parameters.SshCommandParameter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

import static edu.kpi.lab01.constants.Constants.Format.BREAK;
import static edu.kpi.lab01.constants.Constants.Format.LINE_ENDING;
import static edu.kpi.lab01.constants.Constants.Parameters.*;

public class SshCommandUtils {

    public static SshCommandParameter getCommandParameter(final HttpServletRequest request) {

        return SshCommandParameter.builder()
                .username(request.getParameter(USERNAME))
                .host(request.getParameter(HOST))
                .port(Integer.parseInt(request.getParameter(PORT)))
                .password(request.getParameter(PASSWORD))
                .command(request.getParameter(COMMAND))
                .build();
    }

    public static void populateModelWithParameters(final HttpServletRequest request, final SshCommandParameter sshCommandParameter) {

        request.setAttribute(USERNAME, sshCommandParameter.getUsername());
        request.setAttribute(HOST, sshCommandParameter.getHost());
        request.setAttribute(PORT, sshCommandParameter.getPort());
        request.setAttribute(PASSWORD, sshCommandParameter.getPassword());
    }

    public static void populateModelWithResult(final HttpServletRequest request, final String result) {

        request.setAttribute(RESULT, String.join(BREAK, result.split(LINE_ENDING)));
    }
}
