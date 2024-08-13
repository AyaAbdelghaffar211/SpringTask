package com.example.demo.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class CustomHeaderFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String headerValue = ((HttpServletRequest) servletRequest).getHeader("x-validation-report");

        if (headerValue == null || !headerValue.equalsIgnoreCase("true")) {
            ((HttpServletResponse)servletResponse).sendError(HttpStatus.BAD_REQUEST.value(), "Required header 'X-Required-Header' must be set to 'true'");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
