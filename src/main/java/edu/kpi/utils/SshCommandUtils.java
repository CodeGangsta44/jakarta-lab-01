package edu.kpi.utils;

import edu.kpi.dto.ConnectionDto;
import edu.kpi.parameters.SshCommandParameter;
import jakarta.servlet.http.HttpServletRequest;

import static edu.kpi.constants.Constants.Format.BREAK;
import static edu.kpi.constants.Constants.Format.LINE_ENDING;
import static edu.kpi.constants.Constants.Parameters.*;

public class SshCommandUtils {

    public static SshCommandParameter getCommandParameter(final HttpServletRequest request) {

        return SshCommandParameter.builder()
                .connection(ConnectionUtils.getConnectionParameter(request))
                .command(request.getParameter(COMMAND))
                .build();
    }

    public static void populateModelWithParameters(final HttpServletRequest request, final SshCommandParameter parameter) {

        request.setAttribute(CONNECTION, parameter.getConnection());
    }

    public static void populateModelWithResult(final HttpServletRequest request, final String result) {

        request.setAttribute(RESULT, String.join(BREAK, result.split(LINE_ENDING)));
    }
}
