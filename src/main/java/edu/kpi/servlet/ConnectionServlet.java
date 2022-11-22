package edu.kpi.servlet;

import edu.kpi.service.ConnectionServiceLocal;
import edu.kpi.utils.ConnectionUtils;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import static edu.kpi.constants.Constants.Methods.*;
import static edu.kpi.constants.Constants.Parameters.ID;
import static edu.kpi.constants.Constants.Parameters.METHOD;

@WebServlet(name = "ConnectionServlet", value = "/connection")
public class ConnectionServlet extends HttpServlet {

    @EJB
    private ConnectionServiceLocal connectionService;

    @FunctionalInterface
    interface ServletBiConsumer<T, U> {

        void accept(T t, U u) throws ServletException, IOException;
    }


    private final Map<String, ServletBiConsumer<HttpServletRequest, HttpServletResponse>> methodMapping = Map.ofEntries(
            Map.entry(POST, this::doPostInternal),
            Map.entry(DELETE, this::doDelete),
            Map.entry(PUT, this::doPut));

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        Optional.ofNullable(request.getParameter(METHOD))
                .map(methodMapping::get)
                .orElseThrow(UnsupportedOperationException::new)
                .accept(request, response);
    }

    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        connectionService.deleteById(Long.parseLong(request.getParameter(ID)));

        response.sendRedirect(request.getContextPath() + "/home");
    }

    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        connectionService.updateConnection(ConnectionUtils.getConnectionParameter(request));

        response.sendRedirect(request.getContextPath() + "/home");
    }

    private void doPostInternal(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        connectionService.addConnection(ConnectionUtils.getConnectionParameter(request));

        response.sendRedirect(request.getContextPath() + "/home");
    }
}
