package ru.evilnoob.project.auth.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_BEARER_START_STRING = "Bearer ";
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String token = getToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromToken(token));
        if (userDetails != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    Optional.ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(new ArrayList<>()));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private String getToken(String authHeader) {
        return (isEmpty(authHeader) || !authHeader.startsWith(TOKEN_BEARER_START_STRING))
                ? null
                : authHeader.substring(TOKEN_BEARER_START_STRING.length()).trim();
    }

    private String getUsernameFromToken(String token) {
        if (token == null) {
            return null;
        }
        try {
            return jwtTokenHelper.getUsernameFromToken(token);
        } catch (IllegalArgumentException e) {
            logger.error("Error occurred during getting username from token.", e);
        } catch (ExpiredJwtException e) {
            logger.error("Token expired and is not valid anymore.", e);
        } catch (JwtException e) {
            logger.error("Cannot parse token.", e);
        }

        return null;
    }
}
