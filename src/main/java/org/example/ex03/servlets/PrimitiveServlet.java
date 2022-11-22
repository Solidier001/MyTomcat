package org.example.ex03.servlets;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
* This is a Test Program for HttpServer1
* */

public class PrimitiveServlet implements Servlet {

    public PrimitiveServlet() {
    }

    public void init(ServletConfig config) throws ServletException{
        System.out.println("init");
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("from service");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<h1>SUCCESS</h1>");
    }

    public void destroy(){
        System.out.println("destroy");
    }

    public String getServletInfo(){
        return null;
    }

    public ServletConfig getServletConfig(){
        return null;
    }
}
