package com.br.rbs.sherlock.api;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main class to test Jetty Configuration
 * User: helmedeiros
 * Date: 8/24/13
 * Time: 4:31 PM
 */
public class Sherlock extends AbstractHandler {

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Sherlock<h1>");
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new Sherlock());

        server.start();
        server.join();
    }
}
