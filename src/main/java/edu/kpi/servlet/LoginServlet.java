package edu.kpi.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Optional;

import static edu.kpi.constants.Constants.Headers.REFERER;
import static edu.kpi.constants.Constants.Pages.HOME;
import static edu.kpi.constants.Constants.Pages.LOGIN;
import static edu.kpi.constants.Constants.Parameters.AFTER_REGISTRATION;
import static edu.kpi.constants.Constants.Parameters.FAILED_ATTEMPT;

@WebServlet(name = "LoginServlet", value = {"/login", "/login-error"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        if (request.getHeader(REFERER).contains("register")) {

            request.setAttribute(AFTER_REGISTRATION, Boolean.TRUE);
        }
        if (request.getServletPath().contains("login-error")) {

            request.setAttribute(FAILED_ATTEMPT, Boolean.TRUE);
        }

        request.getRequestDispatcher(LOGIN)
                .forward(request, response);
    }
}
