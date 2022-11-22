package edu.kpi.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static edu.kpi.constants.Constants.Pages.ROOT;

@WebServlet(name = "RootServlet")
public class RootServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher(ROOT)
                .forward(request, response);
    }
}
