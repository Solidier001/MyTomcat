package org.example.ex03.connector.http;

import org.example.ex02.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ServletProcessor implements Processer {

    @Override
    public void process(HttpRequest request, HttpResponse response)  {
        String uri=request.getRequestURI();
        String servletName=uri.substring(uri.lastIndexOf('/')+1);
        URLClassLoader loader=null;
        try {
            URL[] urls=new URL[1];
            URLStreamHandler streamHandler=null;
            File classPath=new File(Constants.WEB_ROOT);
            String repository=new URL("file",null,classPath.getCanonicalPath()+File.separator).toString();
            urls[0]=new URL(null,repository,streamHandler);
            loader=new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myclass=null;
        try {
            myclass=loader.loadClass("org.example.ex03.servlets."+servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Servlet servlet=null;
        try {
            servlet= (Servlet) myclass.getConstructor().newInstance();
            HttpRequestFacade requestFacade=new HttpRequestFacade(request);
            HttpResponseFacade responseFacade=new HttpResponseFacade(response);
            servlet.service(requestFacade,responseFacade);
        }  catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
