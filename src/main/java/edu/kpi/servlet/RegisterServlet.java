package edu.kpi.servlet;

import edu.kpi.dto.RegistrationStatus;
import edu.kpi.parameters.RegisterParameter;
import edu.kpi.service.UserServiceLocal;
import edu.kpi.utils.AuthUtils;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.kpi.constants.Constants.Pages.REGISTER;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserServiceLocal userService;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(REGISTER)
                .forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final RegisterParameter registerParameter = AuthUtils.getRegisterParameter(request);

        RegistrationStatus status = RegistrationStatus.SUCCESS;

        try {

            userService.registerUser(registerParameter);

        } catch (final Exception e) {

            status = AuthUtils.getRegistrationStatus(e);
        }

        AuthUtils.populateModelWithRegisterParameters(request, registerParameter);
        AuthUtils.populateModelWithRegisterStatus(request, status);

        if (RegistrationStatus.SUCCESS.equals(status)) {

            response.sendRedirect(request.getContextPath() + "/home");

        } else {

            request.getRequestDispatcher(REGISTER)
                    .forward(request, response);
        }
    }
}
