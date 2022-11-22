package edu.kpi.servlet;

import edu.kpi.converter.ConnectionConverterLocal;
import edu.kpi.converter.HistoryEntryConverterLocal;
import edu.kpi.dto.HistoryEntryDto;
import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import edu.kpi.service.ConnectionServiceLocal;
import edu.kpi.service.HistoryEntryServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static edu.kpi.constants.Constants.Methods.DELETE;
import static edu.kpi.constants.Constants.Pages.HISTORY;
import static edu.kpi.constants.Constants.Parameters.*;

@WebServlet(name = "HistoryServlet", value = "/history")
public class HistoryServlet extends HttpServlet {

    @EJB
    private ConnectionServiceLocal connectionService;
    @EJB
    private HistoryEntryServiceLocal historyEntryService;
    @EJB
    private HistoryEntryConverterLocal historyEntryConverter;
    @EJB
    private ConnectionConverterLocal connectionConverter;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        final Connection connection = Optional.ofNullable(request.getParameter(ID))
                .filter(Predicate.not(String::isEmpty))
                .map(Long::parseLong)
                .map(connectionService::findById)
                .orElseThrow();

        final List<HistoryEntryDto> entries = historyEntryService.findByConnection(connection)
                .stream()
                .sorted(Comparator.comparing(HistoryEntry::getStartTimestamp).reversed())
                .map(historyEntryConverter::convert)
                .collect(Collectors.toList());

        if (entries.isEmpty()) {

            response.sendRedirect(request.getContextPath() + "/home");

        } else {

            request.setAttribute(CONNECTION, connectionConverter.convert(connection));
            request.setAttribute(ENTRIES, entries);

            request.getRequestDispatcher(HISTORY)
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        if (DELETE.equals(request.getParameter(METHOD))) {

            Optional.ofNullable(request.getParameter(HISTORY_ENTRY_ID))
                    .filter(Predicate.not(String::isEmpty))
                    .map(Long::parseLong)
                    .ifPresent(historyEntryService::deleteById);
        }

        response.sendRedirect(request.getContextPath() + "/history?id=" + request.getParameter(ID));
    }
}
