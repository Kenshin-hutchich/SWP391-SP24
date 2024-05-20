/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import dal.AccessLogDAO;
import dtos.UserSessionDto;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author KENSHIN
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) rs;
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        String strURI = request.getRequestURI().replace(request.getContextPath() + "/", "");
        UserSessionDto user = new UserSessionDto();
        //  Grant access for all request for css/js/... resources
        if (strURI.contains("assets")) {
            chain.doFilter(request, response);
            return;
        }
        //  Get user information
        if (cookies != null) {
            String sessionID;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();
                    if (session != null && sessionID.equals(session.getId())) {
                        if ((UserSessionDto) session.getAttribute("userSessionDto") != null) {
                            user = (UserSessionDto) session.getAttribute("userSessionDto");
                        }
                    }
                }
            }
        }
        //  Check if user has access to requesting resources.
        System.out.println(request.getRequestURI());
        System.out.println(request.getMethod());
        System.out.println("Use access control class.");

        AccessControl accessControl = new AccessControl();
        String userIp = request.getRemoteAddr();
        if (accessControl.hasAccess(strURI, user.getId(), userIp)) {
            System.out.println("Done using access control filter");
            chain.doFilter(request, response);
        } else {
            System.out.println("Done using access control filter");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
