package com.jrv.springbootandjavaconcepts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SessionFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Autowired
    SessionRegistry sessionRegistry;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String sessionId = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (sessionId == null || sessionId.length() == 0) {
            chain.doFilter(request, response);
            return;
        }
        String username = sessionRegistry.getUsernameForSession(sessionId);
        if (username == null) {
            chain.doFilter(request, response);
            return;
        }
        User user = userService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
}
