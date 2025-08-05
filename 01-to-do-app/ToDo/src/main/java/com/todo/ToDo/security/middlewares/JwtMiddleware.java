package com.todo.ToDo.security.middlewares;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.todo.ToDo.security.jwt.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtMiddleware extends OncePerRequestFilter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private Jwt jwt;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String headerAuthorization = request.getHeader("Authorization");
        String token;
        String username;

        if(headerAuthorization == null || !headerAuthorization.startsWith("bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        token = headerAuthorization.substring(7);
        username = jwt.extractUsername(token);

        if(username == null || SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails user = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationUser = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());

            if(jwt.isValidToken(user, token)){
                SecurityContextHolder.getContext().setAuthentication(authenticationUser);
            }

            filterChain.doFilter(request, response);
        }
    }
}
