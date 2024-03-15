package org.structure.controller;

import java.io.*;

import jakarta.servlet.http.*;

public class Main extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

    }

    public void destroy() {
    }
}