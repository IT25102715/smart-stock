package com.se2030.smartstock.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Sends anyone without a login session back to the login page.
 * Only the portal pages listed in WebConfig are guarded.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
