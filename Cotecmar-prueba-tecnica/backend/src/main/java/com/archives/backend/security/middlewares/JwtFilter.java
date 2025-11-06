package com.archives.backend.security.middlewares;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.archives.backend.shared.utils.jwt.JwtServices;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    public JwtServices jwtServices;

    @Autowired
    public UserDetailsService userDetailsService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String username = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("AUTH_TOKEN".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            try {
                username = jwtServices.extractUsername(token);
            } catch (Exception e) {
                clearAuthCookie(response);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "No authorize!");
                return;
            }

        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            try {

                if (!jwtServices.isExpiredjwt(token)) {
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(userToken);

                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token expired");
                    clearAuthCookie(response);
                    return;
                }

            } catch (Exception e) {
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void clearAuthCookie(HttpServletResponse response) {
        Cookie expired = new Cookie("AUTH_TOKEN", "");
        expired.setHttpOnly(true);
        expired.setPath("/");
        expired.setMaxAge(0);
        expired.setSecure(false);
        response.addCookie(expired);
    }

    // Ignora rutas públicas
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.equals("/register") || path.equals("/login");
    }
}
