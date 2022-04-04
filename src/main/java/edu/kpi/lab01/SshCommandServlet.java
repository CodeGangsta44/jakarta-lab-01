package edu.kpi.lab01;

import edu.kpi.lab01.parameters.SshCommandParameter;
import edu.kpi.lab01.service.ExecutionService;
import edu.kpi.lab01.service.impl.SshCommandExecutionService;
import edu.kpi.lab01.utils.SshCommandUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static edu.kpi.lab01.constants.Constants.Pages.SSH_COMMAND;

@WebServlet(name = "SshCommandServlet", value = "/ssh-command")
public class SshCommandServlet extends HttpServlet {

    private final ExecutionService<SshCommandParameter> sshExecutionService;

    public SshCommandServlet() {

        sshExecutionService = new SshCommandExecutionService();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(SSH_COMMAND)
                .forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final SshCommandParameter parameter = SshCommandUtils.getCommandParameter(request);
        SshCommandUtils.populateModelWithParameters(request, parameter);
        SshCommandUtils.populateModelWithResult(request, sshExecutionService.execute(parameter));

        request.getRequestDispatcher(SSH_COMMAND)
                .forward(request, response);
    }
}
