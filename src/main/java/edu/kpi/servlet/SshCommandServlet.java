package edu.kpi.servlet;

import edu.kpi.converter.ConnectionConverterLocal;
import edu.kpi.dto.ConnectionDto;
import edu.kpi.model.Connection;
import edu.kpi.model.User;
import edu.kpi.parameters.SshCommandParameter;
import edu.kpi.service.*;
import edu.kpi.utils.SshCommandUtils;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

import static edu.kpi.constants.Constants.Pages.SSH_COMMAND;
import static edu.kpi.constants.Constants.Parameters.CONNECTION;
import static edu.kpi.constants.Constants.Parameters.ID;

@WebServlet(name = "SshCommandServlet", value = "/ssh-command")
public class SshCommandServlet extends HttpServlet {

    @EJB
    private ExecutionServiceLocal<SshCommandParameter> sshExecutionService;
    @EJB
    private ConnectionServiceLocal connectionService;
    @EJB
    private UserServiceLocal userService;
    @EJB(beanName = "defaultConnectionConverter")
    private ConnectionConverterLocal connectionConverter;
    @EJB
    private TransactionalHistoryEntryServiceLocal<String> transactionalHistoryEntryService;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        Optional.ofNullable(request.getParameter(ID))
                .filter(Predicate.not(String::isEmpty))
                .map(Long::parseLong)
                .map(connectionService::findById)
                .filter(this::isOwnedByCurrentUser)
                .map(connectionConverter::convert)
                .ifPresent(connection -> request.setAttribute(CONNECTION, connection));

        request.getRequestDispatcher(SSH_COMMAND)
                .forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final SshCommandParameter parameter = SshCommandUtils.getCommandParameter(request);

        final String result = Optional.ofNullable(parameter.getConnection())
                .map(ConnectionDto::getId)
                .filter(Predicate.not(String::isEmpty))
                .map(Long::valueOf)
                .map(connectionService::findById)
                .map(connection -> transactionalHistoryEntryService.executeAndSaveHistory(connection, parameter.getCommand(),
                        () -> sshExecutionService.execute(parameter)))
                .orElseGet(() -> sshExecutionService.execute(parameter));

        SshCommandUtils.populateModelWithParameters(request, parameter);
        SshCommandUtils.populateModelWithResult(request, result);

        request.getRequestDispatcher(SSH_COMMAND)
                .forward(request, response);
    }

    private boolean isOwnedByCurrentUser(final Connection connection) {

        return userService.getCurrentUser()
                .map(User::getId)
                .filter(id -> connection.getOwner().getId().equals(id))
                .isPresent();
    }
}
