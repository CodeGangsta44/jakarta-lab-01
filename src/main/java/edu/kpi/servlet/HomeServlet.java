package edu.kpi.servlet;

import edu.kpi.constants.Constants;
import edu.kpi.converter.ConnectionConverterLocal;
import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.User;
import edu.kpi.model.facade.ConnectionFacadeLocal;
import edu.kpi.service.ConnectionServiceLocal;
import edu.kpi.service.UserServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static edu.kpi.constants.Constants.Pages.HOME;

@WebServlet(name = "HomeServlet", value = "/home")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"user"}))
public class HomeServlet extends HttpServlet {

    @EJB
    private UserServiceLocal userService;
    @EJB
    private ConnectionServiceLocal connectionFacade;
    @EJB(beanName = "connectionWithStatisticsConverter")
    private ConnectionConverterLocal connectionConverter;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        userService.getCurrentUser()
                        .ifPresent(user -> {
                            request.setAttribute("username", user.getUsername());
                            request.setAttribute("connections", getConnections(user));
                            request.setAttribute(Constants.Parameters.CONNECTIONS_IN_ROW, Constants.Configuration.CONNECTIONS_IN_ROW);
                        });

        request.getRequestDispatcher(HOME)
                .forward(request, response);
    }

    private List<ConnectionDto> getConnections(final User user) {

        return connectionFacade.findByUser(user)
                .stream()
                .map(connectionConverter::convert)
                .collect(Collectors.toList());
    }
}
