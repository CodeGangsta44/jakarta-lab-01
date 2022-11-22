package edu.kpi.filter;

import edu.kpi.service.UserServiceLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Optional;

import static edu.kpi.constants.Constants.Parameters.ID;
import static edu.kpi.constants.Constants.Parameters.USERNAME;

@WebFilter("/*")
public class SessionFilter implements Filter {

    @EJB
    private UserServiceLocal userService;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {

        Optional.ofNullable(request)
                .filter(HttpServletRequest.class::isInstance)
                .map(HttpServletRequest.class::cast)
                .ifPresent(this::populateSessionAttributes);

        filterChain.doFilter(request, response);
    }

    private void populateSessionAttributes(final HttpServletRequest request) {

        userService.getCurrentUser()
                .ifPresent(user -> {
                    request.setAttribute(USERNAME, user.getUsername());
                    request.setAttribute(ID, user.getId().toString());
                });
    }
}
