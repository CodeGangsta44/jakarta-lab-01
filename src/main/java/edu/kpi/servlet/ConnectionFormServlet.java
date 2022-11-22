package edu.kpi.servlet;

import edu.kpi.converter.ConnectionConverterLocal;
import edu.kpi.service.ConnectionServiceLocal;
import edu.kpi.service.UserServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static edu.kpi.constants.Constants.Pages.CONNECTION_FORM;
import static edu.kpi.constants.Constants.Parameters.CONNECTION;
import static edu.kpi.constants.Constants.Parameters.ID;

@WebServlet(name = "ConnectionFormServlet", value = {"/addConnection", "/editConnection"})
public class ConnectionFormServlet extends HttpServlet {

    @EJB
    private UserServiceLocal userService;
    @EJB
    private ConnectionServiceLocal connectionService;
    @EJB(beanName = "defaultConnectionConverter")
    private ConnectionConverterLocal connectionConverter;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        if (request.getServletPath().contains("editConnection")) {

            Optional.ofNullable(request.getParameter(ID))
                    .map(Long::parseLong)
                    .map(connectionService::findById)
                    .map(connectionConverter::convert)
                    .ifPresent(connection -> request.setAttribute(CONNECTION, connection));
        }

        request.getRequestDispatcher(CONNECTION_FORM)
                .forward(request, response);
    }
}
