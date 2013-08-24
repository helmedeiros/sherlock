package com.br.rbs.sherlock.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletTest with jetty
 * User: helmed
 * Date: 8/24/13
 * Time: 4:59 PM
 */
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("<h1>Hello from Sherlock</h1>");
        resp.getWriter().println("session="+req.getSession(true).getId());
    }
}
