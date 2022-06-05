package edu.kpi.utils;

import edu.kpi.dto.RegistrationStatus;
import edu.kpi.exception.PasswordsNotEqualsException;
import edu.kpi.exception.UsernameNotUniqueException;
import edu.kpi.parameters.RegisterParameter;
import edu.kpi.parameters.SshCommandParameter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import static edu.kpi.constants.Constants.Parameters.*;

public class AuthUtils {

    private static final Map<Class<? extends Exception>, RegistrationStatus> REGISTRATION_STATUS_MAP =
            Map.ofEntries(
                    Map.entry(UsernameNotUniqueException.class, RegistrationStatus.NOT_UNIQUE_USERNAME),
                    Map.entry(PasswordsNotEqualsException.class, RegistrationStatus.PASSWORDS_NOT_EQUALS));

    public static RegisterParameter getRegisterParameter(final HttpServletRequest request) {

        return RegisterParameter.builder()
                .username(request.getParameter(USERNAME))
                .password(request.getParameter(PASSWORD))
                .repeatedPassword(request.getParameter(REPEATED_PASSWORD))
                .build();
    }

    public static RegistrationStatus getRegistrationStatus(final Exception exception) {

        return REGISTRATION_STATUS_MAP.get(exception.getClass());
    }

    public static void populateModelWithRegisterParameters(final HttpServletRequest request, final RegisterParameter parameter) {

        request.setAttribute(USERNAME, parameter.getUsername());
        request.setAttribute(PASSWORD, parameter.getPassword());
        request.setAttribute(REPEATED_PASSWORD, parameter.getRepeatedPassword());
    }

    public static void populateModelWithRegisterStatus(final HttpServletRequest request, final RegistrationStatus status) {

        request.setAttribute(STATUS, status);
    }
}