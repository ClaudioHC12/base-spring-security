package com.curso.base_spring_security.config.filter;

import com.curso.base_spring_security.entity.User;
import com.curso.base_spring_security.repository.UserRepository;
import com.curso.base_spring_security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //1.Obtener el header que contiene el jwt
        String authHeader = request.getHeader("Authorization");//Bearer jwt
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //2.Obtener el jwt desde el header
        String jwt = authHeader.split(" ")[1];

        //3.Obtener subject/username desde el jwt
        String userName = jwtService.extractUserName(jwt);

        //4.Setear un objeto Authentication dentro del securityContext
        User user = userRepository.findByUserName(userName).get();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userName, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //5.Ejecutar el resto de filtros
        filterChain.doFilter(request, response);
    }
}
