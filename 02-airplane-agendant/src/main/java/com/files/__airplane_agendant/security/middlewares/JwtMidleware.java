package com.files.__airplane_agendant.security.middlewares;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.files.__airplane_agendant.security.jwt.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtMidleware extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final Jwt jwt;

    @Override
    @SuppressWarnings("null")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String headerAuthorization = request.getHeader("Authorization");
        String token;
        String username;

        if (headerAuthorization == null || !headerAuthorization.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = headerAuthorization.substring(7);
        username = jwt.getUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationUser = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            if (jwt.isValidToken(userDetails, token)) {
                SecurityContextHolder.getContext().setAuthentication(authenticationUser);
            }

            filterChain.doFilter(request, response);
        }
    }
}
